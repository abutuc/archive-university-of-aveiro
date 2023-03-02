"""Middleware to communicate with PubSub Message Broker."""
from collections.abc import Callable
from enum import Enum
from queue import LifoQueue, Empty
import socket
from typing import Any, Tuple

from .protocol import ListReplyMessage, PubSubProto, PublishMessage, Serializer


class MiddlewareType(Enum):
    """Middleware Type."""

    CONSUMER = 1
    PRODUCER = 2


class Queue:
    """Representation of Queue interface for both Consumers and Producers."""

    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        """Create Queue."""
        self._topic = topic
        self._type = _type
        self._broker_host = "localhost"
        self._broker_port = 5000

        self._sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create socket
        self._sock.connect((self._broker_host, self._broker_port))

        if self._type == MiddlewareType.CONSUMER:
            self.subscribe()

    @property
    def topic(self):
        return self._topic

    def push(self, value):
        """Sends data to broker."""
        publish_msg = PubSubProto.publish(self._format, self._topic, value)
        PubSubProto.send_msg(self._sock, publish_msg)

    def pull(self) -> Tuple[str, Any]:
        """Receives (topic, data) from broker.

        Should BLOCK the consumer!"""
        publish_msg: PublishMessage = PubSubProto.recv_msg(self._sock)  # Should be PublishMessage        
        topic = publish_msg.topic
        data = publish_msg.data

        return (topic, data)

    def list_topics(self, callback: Callable):
        """Lists all topics available in the broker."""        
        list_request_msg = PubSubProto.list_request(self._format)
        PubSubProto.send_msg(self._sock, list_request_msg)
        list_reply_msg: ListReplyMessage = PubSubProto.recv_msg(self._sock) # Should be ListReplyMessage
        callback(list_reply_msg.topics) # Feed the callback with topics list (callback would be a function inside the consumer that accepts the list as an argument)

    def subscribe(self):
        """Create subscription."""
        subscribe_msg = PubSubProto.subscribe(self._format, self._topic)
        PubSubProto.send_msg(self._sock, subscribe_msg)

    def cancel(self):
        """Cancel subscription."""
        cancel_msg = PubSubProto.cancel(self._format, self._topic)
        PubSubProto.send_msg(self._sock, cancel_msg)


class JSONQueue(Queue):
    """Queue implementation with JSON based serialization."""

    def __init__(self, topic, _type):
        """Create Queue."""
        self._format = Serializer.JSON
        super().__init__(topic, _type)


class XMLQueue(Queue):
    """Queue implementation with XML based serialization."""

    def __init__(self, topic, _type):
        """Create Queue."""
        self._format = Serializer.XML
        super().__init__(topic, _type)


class PickleQueue(Queue):
    """Queue implementation with Pickle based serialization."""

    def __init__(self, topic, _type):
        """Create Queue."""
        self._format = Serializer.PICKLE
        super().__init__(topic, _type)
