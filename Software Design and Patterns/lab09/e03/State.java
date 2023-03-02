public class State<T> {
    private String operation;
    private T value;

    public State(String operation, T value){
        this.operation = operation;
        this.value = value;
    }
    public String getOperation() {
        return operation;
    }
    public T getValue() {
        return value;
    }
    
}
