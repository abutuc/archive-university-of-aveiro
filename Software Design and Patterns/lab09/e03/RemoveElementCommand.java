public class RemoveElementCommand<T> implements Command<T>{
    private T element;

    public RemoveElementCommand(T element){
        this.element = element;
    }

    @Override
    public void execute(MyCollection<T> collection) {
        collection.remove(element);
    }

}
