package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B3015 {

    public static class Person {

        int height;
        int overlap;

        public Person(int height, int overlap) {
            this.height = height;
            this.overlap = overlap;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long ans = 0;
        Stack<Person> stk = new Stack<>();
        while (N-- > 0) {
            int height = Integer.parseInt(br.readLine());

            int count = 0;
            int overlap = 1;
            while (!stk.isEmpty() && stk.peek().height <= height) {
                if (stk.peek().height == height) {
                    overlap += stk.peek().overlap;
                }
                count += stk.pop().overlap;
            }

            ans += stk.isEmpty() ? count : count + 1;
            stk.push(new Person(height, overlap));
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
