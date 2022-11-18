public class UndoCommand<T> implements Command<T>{
    @Override
    public void execute(MyCollection<T> collection) {
        collection.undo();
    }
}
