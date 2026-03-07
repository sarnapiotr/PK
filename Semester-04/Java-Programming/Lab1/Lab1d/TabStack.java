package Lab1d;

public class TabStack {
    private String[] stack = new String[20];
    private int size = 0;

    public String pop(){
        size--;
        return stack[size];
    }

    public void push(String a){
        stack[size] = a;
        size++;
    }

    public String toString(){
        String tmp = "";
        for(int i = 0; i < size; i++)
            tmp += stack[i] + " ";
        return tmp;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int i){
        size = i;
    }

    public String showValue(int i){
        if(i < size)
            return stack[i];
        else return null;
    }
}