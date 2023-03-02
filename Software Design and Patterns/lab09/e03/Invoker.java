public class Invoker<T> {
    private Command<T> command;

    public void setCommand(Command<T> command){
        this.command = command;
    }

    public void executeCommand(MyCollection<T> collection){
        command.execute(collection);
    }
}
