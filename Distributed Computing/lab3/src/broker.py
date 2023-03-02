"""Message Broker"""
import selectors
import socket
from typing import Dict, List, Tuple

from .protocol import Message, PubSubProto, Serializer


class Topic:
    def __init__(self):
        self._data = None
        self._subtopics: Dict[str, Topic] = {}  # key: topic name ; value: Topic
        self._subscribers = {}  # key: connection ; value: format

    def get_data(self, topic_path: List[str]):
        """Returns stored data inside given topic."""
        if len(topic_path) == 0:
            # We are inside the desired topic
            return self._data
        else:
            # Continue to next subtopic
            subtopic = topic_path.pop(0)
            if subtopic not in self._subtopics.keys():
                return None  # No data, subtopic is non existent
            return self._subtopics[subtopic].get_data(topic_path)

    def put_data(self, topic_path: List[str], data: str):
        """Stores data inside given topic."""
        if len(topic_path) == 0:
            # We are inside the desired topic
            self._data = data
        else:
            # Continue to next subtopic
            subtopic = topic_path.pop(0)
            if subtopic not in self._subtopics.keys():
                self._subtopics[subtopic] = Topic()  # Create subtopic if non existent
            self._subtopics[subtopic].put_data(topic_path, data)

    def add_subscriber(self, topic_path: List[str], conn: socket.socket, format: Serializer):
        """Subscribes consumer to topic (and subtopics implicitly)."""
        if len(topic_path) == 0:
            # We are inside the desired topic
            self._subscribers[conn] = format
        else:
            # Continue to next subtopic
            subtopic = topic_path.pop(0)
            if subtopic not in self._subtopics.keys():
                self._subtopics[subtopic] = Topic()  # Create subtopic if non existent
            self._subtopics[subtopic].add_subscriber(topic_path, conn, format)

    def remove_subscriber(self, topic_path: List[str], conn: socket.socket):
        """Unsubscribe consumer to topic (and subtopics implicitly)."""
        if len(topic_path) == 0:
            # We are inside the desired topic
            if conn in self._subscribers:
                self._subscribers.pop(conn)
            for subtopic in self._subtopics:
                # Unsubscribe possibly subscribed subtopics
                self._subtopics[subtopic].remove_subscriber([], conn)
        else:
            # Continue to next subtopic
            subtopic = topic_path.pop(0)
            if subtopic not in self._subtopics.keys():
                return  # Do nothing, subtopic is non existent
            self._subtopics[subtopic].remove_subscriber(topic_path, conn)

    def list_topics(self):
        """Returns a list of strings containing all topics containing values."""
        if len(self._subtopics) == 0:
            # There are no subtopics
            return []
        else:
            topic_list = []
            for subtopic in self._subtopics.keys():
                subtopic_data = self._subtopics[subtopic].get_data([])
                subtopic_list = self._subtopics[subtopic].list_topics()
                if subtopic_data != None:
                    # Include topic if it contains data
                    topic_list.append(subtopic)
                for path in subtopic_list:
                    # Include subtopics that contain data
                    topic_list.append(subtopic + "/" + path)
            return topic_list

    def list_subscribers(self, topic_path: List[str]) -> List[Tuple[socket.socket, Serializer]]:
        """Returns a list of all consumers subscribed to a given topic."""
        if len(topic_path) == 0:
            # We are inside the desired topic
            return list(self._subscribers.items())
        else:
            # Continue to next subtopic
            subtopic = topic_path.pop(0)
            if subtopic not in self._subtopics.keys():
                return []  # No subscribers, subtopic is non existent

            # Subscribers of a superior topic are also subscribed to inferior topics
            subscribers_list = list(self._subscribers.items()) + self._subtopics[subtopic].list_subscribers(topic_path)
            subscribers_list = list(dict.fromkeys(subscribers_list))  # Remove possible duplicates
            return subscribers_list


class Broker:
    """Implementation of a PubSub Message Broker."""

    def __init__(self):
        """Initialize broker."""
        self.canceled = False
        self._root_topic = Topic()

        self._host = "localhost"
        self._port = 5000

        self._sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create socket
        self._sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)  # Reuse socket
        self._sock.bind((self._host, self._port))

        self._sel = selectors.DefaultSelector()  # Create selector
        self._sel.register(self._sock, selectors.EVENT_READ, self.accept)  # Wait for connections

        self._sock.listen(5)  # 5 concurrent clients

    def accept(self, sock: socket.socket):
        """Accept a connection."""
        conn, addr = sock.accept()
        conn.setblocking(False)
        self._sel.register(conn, selectors.EVENT_READ, self.read)  # Read data from connection

    def read(self, conn):
        """Reads received data and takes appropriate action."""
        msg: Message = PubSubProto.recv_msg(conn)

        if msg == None:  # Connection ended
            self._sel.unregister(conn)
            self._root_topic.remove_subscriber([], conn)
            conn.close()
            return

        if msg.command == "subscribe":
            self.subscribe(msg.topic, conn, msg.format)
            latest_data = self.get_topic(msg.topic)
            if latest_data != None:
                publish_msg = PubSubProto.publish(msg.format, msg.topic, latest_data)
                PubSubProto.send_msg(conn, publish_msg)

        elif msg.command == "publish":
            self.put_topic(msg.topic, msg.data)
            for conn, format in self.list_subscriptions(msg.topic):
                publish_msg = PubSubProto.publish(format, msg.topic, msg.data)
                PubSubProto.send_msg(conn, publish_msg)

        elif msg.command == "list_request":
            list_reply_msg = PubSubProto.list_reply(msg.format, self.list_topics())
            PubSubProto.send_msg(conn, list_reply_msg)

        elif msg.command == "cancel":
            self.unsubscribe(msg.topic, conn)

    def list_topics(self) -> List[str]:
        """Returns a list of strings containing all topics containing values."""
        return self._root_topic.list_topics()

    def get_topic(self, topic: str):
        """Returns the currently stored value in topic."""
        topic_path = topic.rsplit("/", topic.count("/") - 1)
        return self._root_topic.get_data(topic_path)

    def put_topic(self, topic: str, value: str):
        """Store in topic the value."""
        topic_path = topic.rsplit("/", topic.count("/") - 1)
        self._root_topic.put_data(topic_path, value)

    def list_subscriptions(self, topic: str) -> List[Tuple[socket.socket, Serializer]]:
        """Provide list of subscribers to a given topic."""
        topic_path = topic.rsplit("/", topic.count("/") - 1)
        return self._root_topic.list_subscribers(topic_path)

    def subscribe(self, topic: str, address: socket.socket, _format: Serializer = None):
        """Subscribe to topic by client in address."""
        topic_path = topic.rsplit("/", topic.count("/") - 1)
        self._root_topic.add_subscriber(topic_path, address, _format)

    def unsubscribe(self, topic: str, address: socket.socket):
        """Unsubscribe to topic by client in address."""
        topic_path = topic.rsplit("/", topic.count("/") - 1)
        self._root_topic.remove_subscriber(topic_path, address)

    def run(self):
        """Run until canceled."""

        while not self.canceled:
            events = self._sel.select()
            for key, mask in events:
                callback = key.data
                callback(key.fileobj)
