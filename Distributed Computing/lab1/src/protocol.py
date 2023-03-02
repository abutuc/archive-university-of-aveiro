"""Protocol for chat server - Computação Distribuida Assignment 1."""
import json
import socket
from datetime import datetime

HOST = 'localhost'
PORT = 2000 # 1024 para baixo está reservado para o sudo.

class Message:
    """Message Type."""
    def __init__(self, command):
        self.command = command
    
    def __str__(self) -> str:
        return '{{"command": "{0}"}}'.format(self.command)
    

class JoinMessage(Message):
    """Message to join a chat channel."""
    def __init__(self, command, channel):
        super().__init__(command)
        self.channel = channel
        self.json_obj = json.dumps({"command": "join", "channel": self.channel}).encode('utf-8')

    def __str__(self) -> str:
        return '{{"command": "{0}", "channel": "{1}"}}'.format(self.command, self.channel)


class RegisterMessage(Message):
    """Message to register username in the server."""
    def __init__(self, command, user):
        super().__init__(command)
        self.user = user
        self.json_obj = json.dumps({"command": "register", "user": self.user}).encode('utf-8')
    
    def __str__(self) -> str:
        return '{{"command": "{0}", "user": "{1}"}}'.format(self.command, self.user)

    
class TextMessage(Message):
    """Message to chat with other clients."""
    def __init__(self,command,message,channel):
        super().__init__(command)
        self.message = message
        self.channel = channel
        self.ts = int(datetime.now().timestamp())
        if (channel == None):
            self.json_obj = json.dumps({"command": "message", "message": self.message, "ts":self.ts}).encode('utf-8')
        else:
            self.json_obj = json.dumps({"command": "message", "message": self.message, "channel": self.channel, "ts":self.ts}).encode('utf-8')

    def __str__(self):
        if(self.channel!=None):
            return '{{"command": "{0}", "channel": "{1}", "message": "{2}", "ts": {3}}}'.format(self.command, self.channel, self.message, self.ts)
        else:
            return '{{"command": "{0}", "message": "{1}", "ts": {2}}}'.format(self.command, self.message, self.ts)



class CDProto:
    """Computação Distribuida Protocol."""

    @classmethod
    def register(cls, username: str) -> RegisterMessage:
        """Creates a RegisterMessage object."""
        return RegisterMessage("register", username)

    @classmethod
    def join(cls, channel: str) -> JoinMessage:
        """Creates a JoinMessage object."""
        return JoinMessage("join", channel)

    @classmethod
    def message(cls, message: str, channel: str = None) -> TextMessage:
        """Creates a TextMessage object."""
        return TextMessage("message", message, channel)

    @classmethod
    def send_msg(cls, connection: socket, msg: Message):
        """Sends through a connection a Message object."""
        header = len(msg.json_obj).to_bytes(2, "big")   # Calculates size of json object according to the paper
        connection.sendall(header + msg.json_obj)       #enviar à socket que está ligada o tamanho + a mensagem

    @classmethod
    def recv_msg(cls, connection: socket) -> Message:
        """Receives through a connection a Message object."""
        header = connection.recv(2)                         # Read first two bytes that contain json object size
        if not header:                                      
            raise ConnectionError()                           

        header = int.from_bytes(header, "big")              # Reverse the conversion to int value
        msg = connection.recv(header).decode('UTF-8')       # Read header size from socket and decode it

        try:
            dic_json = json.loads(msg)      # Makes json object into python dict
            command = dic_json["command"]
            if(command=="join"):
                return CDProto.join(dic_json["channel"])
            elif(command=="register"):
                return CDProto.register(dic_json["user"])
                
            elif(command=="message"):
                mensagem = dic_json["message"]
                if("channel" in dic_json):
                    channel = dic_json["channel"]
                    return CDProto.message(mensagem,channel)
                else:
                    return CDProto.message(mensagem)
        except:
            raise CDProtoBadFormat()


class CDProtoBadFormat(Exception):
    """Exception when source message is not CDProto."""

    def __init__(self, original_msg: bytes=None) :
        """Store original message that triggered exception."""
        self._original = original_msg

    @property
    def original_msg(self) -> str:
        """Retrieve original message as a string."""
        return self._original.decode("utf-8")
