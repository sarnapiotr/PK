package Lab03b;

public class DoubleClass implements Comparable<DoubleClass> {
    private double val;

    public DoubleClass(double val) {
        this.val = val;
    }

    @Override
    public int compareTo(DoubleClass o) {
        return Double.compare(this.val, o.val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
