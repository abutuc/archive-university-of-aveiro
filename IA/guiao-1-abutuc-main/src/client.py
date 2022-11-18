"""CD Chat client program"""
import logging
import socket
import sys
import fcntl
import os
import selectors

from src.protocol import CDProto, CDProtoBadFormat, JoinMessage, RegisterMessage, TextMessage

logging.basicConfig(filename=f"{sys.argv[0]}.log", level=logging.DEBUG)

HOST = 'localhost' # host local
PORT = 2000 # 1024 para baixo está reservado para o sudo.

class Client:

    """Chat Client process."""
    def __init__(self, name: str = "Foo"):
        """Initializes chat client."""
        self.name = name                            # Sets name argument to Client's name attribute.
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)    # Creates Client's socket.
        self.sel = selectors.DefaultSelector() # Creates Client's selectors.
        self.channel = None                 # Set attribute channel to None, the user does not have an initial channel.
        

    def connect(self):
        """Connect to chat server and setup stdin flags."""
        self.sock.connect((HOST, PORT))                                     # Connect Client's Socket to the Server (HOST, PORT)
        self.sel.register(self.sock, selectors.EVENT_READ, self.read)       # Registers Client's socket in the Client's Selector to monitor event
        message = CDProto.register(self.name)                           # Cria um objeto do tipo RegisterMessage   
        CDProto.send_msg(self.sock, message)                            # Envia esse objeto para o Servidor
        logging.debug('sent "%s"', message)                             # Envia para o ficheiro debug o objeto

    def read(self, conn, mask):
        """Read message from server's socket """
        msg = CDProto.recv_msg(self.sock)       # Lê conteúdo da socket e retorna para msg em formato de Objeto do tipo Message
        logging.debug('received "%s', msg)      # Envia para o ficheiro debug o objeto
        if (type(msg) == TextMessage):          # Se a mensagem for do tipo TextMessage então pode escrevê-la no terminal.
            print(msg.message)

        elif (type(msg) == JoinMessage):                                # Se a mensagem for do tipo JoinMessage então imprime mensagem de sucesso.
            print("\nJoined {} successfully.".format(msg.channel))
        
        elif (type(msg) == RegisterMessage):                            # Se a mensagem for do tipo RegisternMessage então imprime mensagem de sucesso.
            print("\nConnected to the server successfully.")
        else:                                                           # Caso contrário, um erro deve ter acontecido.
            print("\nError occured.")


    def got_keyboard_data(self, stdin, mask): 
        """Detects keybord action and prevents input blocking."""                         
        input_message = stdin.read()                            # Reads input content
        input_content = input_message.strip().split(" ")        # Strips content, removing newline special char, and splits with " " delimiter

        if (input_content[0] == "/join"):                       # one possible command, it must be the first that a Client types.
            message = CDProto.join(input_content[1])            # creates JoinMessage object with input_content[1] as channel argument
            CDProto.send_msg(self.sock, message)                # sends object to server
            self.channel = message.channel

        elif (input_content[0]=="exit"):                        # one possibile command
            self.sel.unregister(self.sock)
            self.sock.close()
            sys.exit()

        else:
            msg = CDProto.message(input_message, self.channel)
            CDProto.send_msg(self.sock, msg)

    

    def loop(self):
        """Loop indefinetely."""

        orig_fl = fcntl.fcntl(sys.stdin, fcntl.F_GETFL)
        fcntl.fcntl(sys.stdin, fcntl.F_SETFL, orig_fl | os.O_NONBLOCK)
        self.sel.register(sys.stdin, selectors.EVENT_READ, self.got_keyboard_data) 
        
        while True:
            sys.stdout.write('>>>')
            sys.stdout.flush()
            for k, mask in self.sel.select():
                callback = k.data
                callback(k.fileobj, mask)


