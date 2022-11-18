import java.util.Collection;
import java.util.Stack;

public class MyCollection<T>{
    Collection<T> collection;
    Stack<State<T>> stack;
    T last_added;
    T last_removed;
    String last_operation;

    public MyCollection(Collection<T> collection){
        this.collection = collection;
        stack = new Stack<>();
    }

    public boolean add(T elem){
        stack.push(new State<T>("add", elem));
        return collection.add(elem);
    }

    public boolean remove(T elem){
        stack.push(new State<T>("remove", elem));
        return collection.remove(elem);
    }

    public boolean undo(){
        State<T> state = stack.pop();
        switch(state.getOperation()){
            case "add":
                return collection.remove(state.getValue());
            case "remove":
                return collection.add(state.getValue());
            default:
                return false;
        }
    }


    public Collection<T> getCollection(){
        return collection;
    }


    
}
