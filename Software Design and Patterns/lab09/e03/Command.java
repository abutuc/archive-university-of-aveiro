public interface Command<T> {
    public void execute(MyCollection<T> collection);
}
