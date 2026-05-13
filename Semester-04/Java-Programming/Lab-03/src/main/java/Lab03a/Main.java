package Lab03a;

public class Main {
    public static void main(String args[]) {
        LinkedStack<Integer> linkedStackInt = new LinkedStack<>();
        System.out.println(linkedStackInt);
        linkedStackInt.push(1);
        linkedStackInt.push(2);
        linkedStackInt.push(3);
        System.out.println(linkedStackInt);
        System.out.println(linkedStackInt.peek());
        linkedStackInt.pop();
        linkedStackInt.pop();
        linkedStackInt.pop();
        System.out.println(linkedStackInt.getSize());

        LinkedStack<String> linkedStackString = new LinkedStack<>();
        System.out.println(linkedStackString);
        linkedStackString.push("One");
        linkedStackString.push("Two");
        linkedStackString.push("Three");
        System.out.println(linkedStackString);
        System.out.println(linkedStackString.peek());
        linkedStackString.pop();
        linkedStackString.pop();
        linkedStackString.pop();
        System.out.println(linkedStackString.getSize());
    }
}
