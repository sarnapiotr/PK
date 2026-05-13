package Lab03b;

public class Set<T extends Comparable<T>> {
    private Comparable[] set;
    private int capacity;
    private int size = 0;

    public Set(int capacity) {
        set = (T[]) new Comparable[capacity];
    }
}
