public class AddElementCommand<T> implements Command<T>{
    private T element;

    public AddElementCommand(T element){
        this.element = element;
    }

    @Override
    public void execute(MyCollection<T> collection) {
        collection.add(element);
    }

}
