package barkingdog.chap22_heap;

public class MyHeap {

    private int[] data = new int[100005];
    int size = 0;

    public void push(int x) {
        data[++size] = x;
        int idx = size;
        while (idx != 1) {
            int parentIdx = idx / 2;
            if (data[parentIdx] <= data[idx]) {
                break;
            }

            swap(idx, parentIdx);
            idx = parentIdx;
        }
    }

    public int top() {
        return data[1];
    }

    public void pop() {
        data[1] = data[size--];
        int idx = 1;
        while (idx * 2 <= size) {
            int lc = idx * 2;
            int rc = idx * 2 + 1;

            int minChild = lc;
            if (rc <= size && data[lc] > data[rc]) {
                minChild = rc;
            }

            if (data[idx] <= data[minChild]) {
                break;
            }

            swap(idx, minChild);
            idx = minChild;
        }
    }

    private void swap(int idx, int parentIdx) {
        int tmp = data[parentIdx];
        data[parentIdx] = data[idx];
        data[idx] = tmp;
    }

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap();

        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 100);
            System.out.println("num = " + num);
            myHeap.push(num);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(myHeap.top());
            myHeap.pop();
        }


    }
}
