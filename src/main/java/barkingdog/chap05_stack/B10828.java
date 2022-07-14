package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10828 {

    public static class MyStack {

        private final int MX = 20000;
        private final int[] dat = new int[MX];
        private int pos = 0;

        public void push(int x) {
            dat[pos++] = x;
        }

        public int pop() {
            if (pos == 0) {
                return -1;
            }

            return dat[--pos];
        }

        public int size() {
            return pos;
        }

        public int empty() {
            if (pos == 0) {
                return 1;
            }

            return 0;
        }

        public int top() {
            if (pos == 0) {
                return -1;
            }

            return dat[pos - 1];
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        MyStack stack = new MyStack();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int dat = Integer.parseInt(st.nextToken());
                    stack.push(dat);
                    break;
                case "pop":
                    bw.write(String.valueOf(stack.pop()));
                    bw.newLine();
                    break;
                case "size":
                    bw.write(String.valueOf(stack.size()));
                    bw.newLine();
                    break;
                case "empty":
                    bw.write(String.valueOf(stack.empty()));
                    bw.newLine();
                    break;
                case "top":
                    bw.write(String.valueOf(stack.top()));
                    bw.newLine();
                    break;
                default:
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
