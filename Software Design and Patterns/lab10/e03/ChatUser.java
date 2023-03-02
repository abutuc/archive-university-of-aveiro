
public class ChatUser extends User {
    
    ChatUser(String name, Group group) {
        super(name, group);
    }

    @Override
    public void send(String msg) {
        System.out.println(name + " sent \"" + msg + "\"");
        group.send(msg);
    }

    @Override
    public void receive(String msg) {
        System.out.println(name + " received \"" + msg + "\"");
    }
}
