
public abstract class User {
    protected String name;
    protected Group group;
    
    User(String name, Group group) {
        this.name = name;
        this.group = group;
    }
    
    public abstract void send(String msg);
    public abstract void receive(String msg);
}
