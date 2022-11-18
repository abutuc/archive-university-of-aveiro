"""CD Chat server program."""
import logging
import socket
import selectors

from src.protocol import CDProto

logging.basicConfig(filename="server.log", level=logging.DEBUG)

HOST = 'localhost'
PORT = 2000 # 1024 para baixo está reservado para o sudo.

class Server:
    """Chat Server process."""

    def __init__(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # server socket
        self.sock.bind((HOST, PORT))
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) # Reutiliza socket
        self.sock.listen(5)
        self.sel = selectors.DefaultSelector()
        self.sel.register(self.sock, selectors.EVENT_READ, self.accept)
        self.connect_clients = list()
        self.channels = dict()

    def accept(self, sock, mask):
        conn, addr = self.sock.accept()  # Should be ready
        conn.setblocking(False)
        self.sel.register(conn, selectors.EVENT_READ, self.read)

    def read(self, conn, mask):
        try:
            data = CDProto.recv_msg(conn)
            logging.debug('received "%s"', data)

            if (data.command == "join"):
                if data.channel not in self.channels.keys():
                    self.channels[data.channel] = set()
                    self.channels[data.channel].add(conn)
                    #print('Channel "{} has been created.'.format(data.channel))
                    #print('{} has joined channel "{}"'.format(conn, data.channel))
                    CDProto.send_msg(conn, data)
                else:
                    if (conn in self.channels[data.channel]):
                        pass
                        #print("User is already in channel, action ignored.")
                    else:
                        self.channels[data.channel].add(conn)
                        #print('{} has joined channel "{}"'.format(conn, data.channel))
                        CDProto.send_msg(conn, data)

            elif (data.command == "register"):
                self.connect_clients.append(conn)
                #print("{} has registered and is now connected to the server.".format(data.user))
            
            elif data.command == "message":
                channel = data.channel
                if (channel == None):
                    for clint in self.connect_clients:
                        CDProto.send_msg(clint, data)
                        #print("{} SENT {} to {}".format(conn, data.message, clint))
                else:
                    for chan, client_set in self.channels.items():
                        if (channel == chan):
                            for cli in client_set:
                                CDProto.send_msg(cli, data)
                                #print("{} SENT {} to {}".format(conn, data.message, cli))

        except ConnectionError:
            #print('closing', conn)
            self.connect_clients.remove(conn)
            self.sel.unregister(conn)
            conn.close()


    def loop(self):
        """Loop indefinetely."""
        while True:
            events = self.sel.select()
            for key, mask in events:
                callback = key.data
                callback(key.fileobj, mask)
