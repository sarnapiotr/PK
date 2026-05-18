package Lab03b;

public class Set<T extends Comparable<T>> {
    private T set[];
    private int capacity;
    private int size = 0;

    public Set(int capacity) {
        set = (T[]) new Comparable[capacity];
        this.capacity = capacity;
    }

    public void addElement(T element) {
        if (size == capacity)
            throw new IllegalStateException("Set full!");

        if (search(element) != -1)
            return;

        int newIndex = 0;
        while (newIndex < size) {
            if (element.compareTo(set[newIndex]) < 0)
                break;

            newIndex++;
        }

        for (int i = size; i > newIndex; i--)
            set[i] = set[i-1];

        set[newIndex] = element;
        size++;
    }

    public void deleteElement(T element) {
        int elementIndex = search(element);

        if (elementIndex != -1) {
            for (int i = elementIndex; i < size-1; i++) {
                set[i] = set[i+1];
            }

            set[--size] = null;
        }
    }

    public int search(T element) {
        for (int i = 0; i < size; i++) {
            if (element.compareTo(set[i]) == 0)
                return i;
        }

        return -1;
    }

    public static <T extends Comparable<T>> Set<T> addElements(Set<T> firstSet, Set<T> secondSet) {
        int firstSize = firstSet.size;
        int secondSize = secondSet.size;

        Set<T> set = new Set<>(firstSize + secondSize);

        for (int i = 0; i < firstSize; i++)
            set.addElement(firstSet.set[i]);

        for (int i = 0; i < secondSize; i++)
            set.addElement(secondSet.set[i]);

        return set;
    }

    public static <T extends Comparable<T>> Set<T> deleteElements(Set<T> firstSet, Set<T> secondSet) {
        int firstSize = firstSet.size;
        Set<T> set = new Set<>(firstSize);

        for (int i = 0; i < firstSize; i++) {
            T tempElement = firstSet.set[i];

            if (secondSet.search(tempElement) == -1)
                set.addElement(tempElement);
        }

        return set;
    }

    public static <T extends Comparable<T>> Set<T> common(Set<T> firstSet, Set<T> secondSet) {
        int firstSize = firstSet.size;
        Set<T> set = new Set<>(firstSize);

        for (int i = 0; i < firstSize; i++) {
            T tempElement = firstSet.set[i];

            if (secondSet.search(tempElement) != -1)
                set.addElement(tempElement);
        }

        return set;
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < size; i++) {
            str += set[i].toString() + " | ";
        }

        return str;
    }
}
