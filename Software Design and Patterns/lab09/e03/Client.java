import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        Invoker<Integer> invoker = new Invoker<>();

        MyCollection<Integer> collection = new MyCollection<>(new ArrayList<Integer>());

        Command<Integer>  addElementCommand = new AddElementCommand<Integer>(4);
        Command<Integer> removeElementCommand = new RemoveElementCommand<Integer>(4);
        Command<Integer> undoCommand = new UndoCommand<>();

        invoker.setCommand(addElementCommand);
        invoker.executeCommand(collection);

        for (Integer elem: collection.getCollection()){
            System.out.println(elem);
        }

        invoker.setCommand(removeElementCommand);
        invoker.executeCommand(collection);

        for (Integer elem: collection.getCollection()){
            System.out.println(elem);
        }
        System.out.println("It printed nothing.");

        invoker.setCommand(undoCommand);
        invoker.executeCommand(collection);

        for (Integer elem: collection.getCollection()){
            System.out.println(elem);
        }
        System.out.println("It worked!");

        invoker.setCommand(addElementCommand);
        for (int i = 0 ; i < 4; i++){
            invoker.executeCommand(collection);
        }
        System.out.println("Filled");

        for (Integer elem: collection.getCollection()){
            System.out.print(elem + " ");
        }
        System.out.println();

        invoker.setCommand(undoCommand);
        for (int i = 0 ; i < 4; i++){
            invoker.executeCommand(collection);
        }

        System.out.println("Emptied");

        for (Integer elem: collection.getCollection()){
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
