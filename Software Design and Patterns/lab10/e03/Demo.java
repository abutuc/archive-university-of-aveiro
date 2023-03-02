
public class Demo {
    public static void main(String[] args) {
        Group chatGroup = new ChatGroup();
        
        User u1 = new ChatUser("Ana", chatGroup);
        User u2 = new ChatUser("Bernardo", chatGroup);
        User u3 = new ChatUser("Catarina", chatGroup);
        User u4 = new ChatUser("Duarte", chatGroup);
        
        chatGroup.addUser(u1);
        chatGroup.addUser(u2);
        chatGroup.addUser(u3);
        chatGroup.addUser(u4);
        
        u1.send("Bom dia pessoal!");
    }
}
