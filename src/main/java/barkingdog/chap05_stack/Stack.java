package barkingdog.chap05_stack;

public class Stack {

    private final static int MX = 10000005;
    private final static int[] dat = new int[MX];
    private static int pos = 0;

    public void push(int x) {
        dat[pos++] = x;
    }

    public void pop() {
        pos--;
    }

    public int top() {
        return dat[pos - 1];
    }

}
