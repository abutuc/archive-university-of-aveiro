"""Protocol for message broker - Computação Distribuida Assignment 3."""
import enum
import json
import pickle
import socket
import xml.etree.ElementTree as ET
from typing import Dict


class Serializer(enum.Enum):
    """Possible message serializers."""

    JSON = 0
    XML = 1
    PICKLE = 2


class Message:
    """Message Type."""

    def __init__(self, command: str, format: Serializer):
        self._command = command
        self._format = format

    @property
    def command(self):
        return self._command

    @property
    def format(self):
        return self._format


class SubscribeMessage(Message):
    """Message to subscribe a topic."""

    def __init__(self, format: Serializer, topic: str):
        super().__init__("subscribe", format)
        self._topic = topic

    @property
    def topic(self):
        return self._topic

    def serialize(self) -> bytes:
        if self._format == Serializer.JSON:
            return json.dumps({"command": self._command, "topic": self._topic}).encode("utf-8")
        elif self._format == Serializer.XML:
            return f"<msg><command>{self._command}</command><topic>{self._topic}</topic></msg>".encode("utf-8")
        elif self._format == Serializer.PICKLE:
            return pickle.dumps({"command": self._command, "topic": self._topic})


class PublishMessage(Message):
    """Message to publish in a topic."""

    def __init__(self, format: Serializer, topic: str, data: str):
        super().__init__("publish", format)
        self._topic = topic
        self._data = data

    @property
    def topic(self):
        return self._topic

    @property
    def data(self):
        return self._data

    def serialize(self) -> bytes:
        if self._format == Serializer.JSON:
            return json.dumps({"command": self._command, "topic": self._topic, "data": self._data}).encode("utf-8")
        elif self._format == Serializer.XML:
            return f"<msg><command>{self._command}</command><topic>{self._topic}</topic><data>{self._data}</data></msg>".encode("utf-8")
        elif self._format == Serializer.PICKLE:
            return pickle.dumps({"command": self._command, "topic": self._topic, "data": self._data})


class ListRequestMessage(Message):
    """Message to request a list of all topics."""

    def __init__(self, format: Serializer):
        super().__init__("list_request", format)

    def serialize(self) -> bytes:
        if self._format == Serializer.JSON:
            return json.dumps({"command": self._command}).encode("utf-8")
        elif self._format == Serializer.XML:
            return f"<msg><command>{self._command}</command></msg>".encode("utf-8")
        elif self._format == Serializer.PICKLE:
            return pickle.dumps({"command": self._command})


class ListReplyMessage(Message):
    """Message to reply with a list of all topics."""

    def __init__(self, format: Serializer, topics: list):
        super().__init__("list_reply", format)
        self._topics = topics

    @property
    def topics(self):
        return self._topics

    def serialize(self) -> bytes:
        if self._format == Serializer.JSON:
            return json.dumps({"command": self._command, "topics": self._topics}).encode("utf-8")
        elif self._format == Serializer.XML:
            return f"<msg><command>{self._command}</command><topics>{self._topics}</topics></msg>".encode("utf-8")
        elif self._format == Serializer.PICKLE:
            return pickle.dumps({"command": self._command, "topics": self._topics})


class CancelMessage(Message):
    """Message to cancel a topic."""

    def __init__(self, format: Serializer, topic: str):
        super().__init__("cancel", format)
        self._topic = topic

    @property
    def topic(self):
        return self._topic

    def serialize(self) -> bytes:
        if self._format == Serializer.JSON:
            return json.dumps({"command": self._command, "topic": self._topic}).encode("utf-8")
        elif self._format == Serializer.XML:
            return f"<msg><command>{self._command}</command><topic>{self._topic}</topic></msg>".encode("utf-8")
        elif self._format == Serializer.PICKLE:
            return pickle.dumps({"command": self._command, "topic": self._topic})


class PubSubProto:
    """PubSub protocol for message broker."""

    @classmethod
    def subscribe(cls, format: Serializer, topic: str) -> SubscribeMessage:
        """Creates a SubscribeMessage object."""
        return SubscribeMessage(format, topic)

    @classmethod
    def publish(cls, format: Serializer, topic: str, data: str) -> PublishMessage:
        """Creates a PublishMessage object."""
        return PublishMessage(format, topic, data)

    @classmethod
    def list_request(cls, format: Serializer) -> ListRequestMessage:
        """Creates a ListRequestMessage object."""
        return ListRequestMessage(format)

    @classmethod
    def list_reply(cls, format: Serializer, topics: list) -> ListReplyMessage:
        """Creates a ListResponseMessage object."""
        return ListReplyMessage(format, topics)

    @classmethod
    def cancel(cls, format: Serializer, topic: str) -> CancelMessage:
        """Creates a CancelMessage object."""
        return CancelMessage(format, topic)

    @classmethod
    def serialize(self, msg: Message) -> bytes:
        return msg.serialize()

    @classmethod
    def unserialize(self, msg: bytes, format: Serializer) -> Dict[str, str]:
        if format == Serializer.JSON:
            return json.loads(msg.decode("utf-8"))
        elif format == Serializer.XML:
            xml_data = ET.fromstring(msg.decode("utf-8"))
            dic_data = {}
            for elem in list(xml_data):                
                dic_data[elem.tag] = elem.text
            return dic_data
        elif format == Serializer.PICKLE:
            return pickle.loads(msg)

    @classmethod
    def send_msg(cls, connection: socket.socket, msg: Message):
        """Sends through a connection a Message object."""
        if connection.fileno() == -1: # Ignore closed connection
            return
        
        m = PubSubProto.serialize(msg)  # serialization
        mfor = int(msg.format.value).to_bytes(1, "big")  # format (1 byte)
        mlen = len(m).to_bytes(1, "big")  # length (1 byte)
        connection.send(mlen + mfor + m)

    @classmethod
    def recv_msg(cls, connection: socket.socket) -> Message:
        """Receives through a connection a Message object."""        
        mlen = int.from_bytes(connection.recv(1), "big")  # length (int)
        if mlen == 0:
            return None

        mfor = Serializer(int.from_bytes(connection.recv(1), "big"))  # format (Serializer)
        m = connection.recv(mlen)
        msg = PubSubProto.unserialize(m, mfor)  # unserialization

        if "command" not in msg:
            raise PubSubProtoBadFormat(m)

        if msg["command"] == "subscribe" and "topic" in msg:
            return PubSubProto.subscribe(mfor, msg["topic"])
        elif msg["command"] == "publish" and "topic" in msg and "data" in msg:
            return PubSubProto.publish(mfor, msg["topic"], msg["data"])
        elif msg["command"] == "list_request":
            return PubSubProto.list_request(mfor)
        elif msg["command"] == "list_reply" and "topics" in msg:
            return PubSubProto.list_reply(mfor, msg["topics"])
        elif msg["command"] == "cancel" and "topic" in msg:
            return PubSubProto.cancel(mfor, msg["topic"])
        else:
            raise PubSubProtoBadFormat(m)


class PubSubProtoBadFormat(Exception):
    """Exception when source message is not PubSubProto."""

    def __init__(self, original_msg: bytes = None):
        """Store original message that triggered exception."""
        self._original = original_msg

    @property
    def original_msg(self) -> str:
        """Retrieve original message as a string."""
        return self._original.decode("utf-8")
