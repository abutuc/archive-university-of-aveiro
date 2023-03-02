import java.util.*;

public class ChatGroup implements Group {

    private List<User> users = new ArrayList<>();
    
    @Override
    public void send(String msg) {
        users.forEach(u -> u.receive(msg));
    }

    @Override
    public void addUser(User u) {
        users.add(u);
    }
}
