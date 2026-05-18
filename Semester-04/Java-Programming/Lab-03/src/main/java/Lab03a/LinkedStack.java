package Lab03a;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class LinkedStack<T> {
    private List<T> stack = new LinkedList<>();

    public void push(T element) {
        stack.addFirst(element);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.removeFirst();
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        return stack.getFirst();
    }

    public int getSize() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
