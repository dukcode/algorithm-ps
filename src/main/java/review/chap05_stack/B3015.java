package review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B3015 {

    static class People {

        int h;
        int cnt;

        public People(int h, int cnt) {
            this.h = h;
            this.cnt = cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        long ans = 0;
        Stack<People> stk = new Stack<>();
        while (n-- > 0) {
            int h = Integer.parseInt(br.readLine());
            int cnt = 1;

            while (!stk.isEmpty() && stk.peek().h <= h) {
                ans += stk.peek().cnt;
                if (stk.peek().h == h) {
                    cnt += stk.peek().cnt;
                }
                stk.pop();
            }

            ans += stk.isEmpty() ? 0 : 1;
            stk.push(new People(h, cnt));
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
