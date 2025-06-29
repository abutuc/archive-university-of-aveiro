package ua.tqs;

import java.util.LinkedList;

public class TqsStack {
    private final LinkedList<Object> stack;

    public TqsStack() {
        stack = new LinkedList<>();
    }

    // Add an item on the top of the stack
    public void push(Object o) {
        stack.add(o);
    }

    // Remove the item at the top
    public Object pop() {
        return stack.removeLast();
    }

    // Return the item at the top (without removing it)
    public Object peek() {
        return stack.getLast();
    }

    // Return the number of items in the stack
    public int size() {
        return stack.size();
    }

    // Return whether the stack is empty
    public boolean isEmpty() {
        return stack.size() == 0;
    }

}