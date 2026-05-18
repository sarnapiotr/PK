package Lab03b;

public class IntegerClass implements Comparable<IntegerClass> {
    private int val;

    public IntegerClass(int val) {
        this.val = val;
    }


    @Override
    public int compareTo(IntegerClass o) {
        return Integer.compare(val, o.val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
