package review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    private static int[] ans;

    private static class Num {

        int idx;
        int num;

        public Num(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        ans = new int[n];

        Stack<Num> stk = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());

            while (!stk.isEmpty() && stk.peek().num < num) {
                ans[stk.peek().idx] = num;
                stk.pop();
            }

            stk.push(new Num(i, num));
        }

        for (int num : ans) {
            if (num == 0) {
                bw.write("-1");
            } else {
                bw.write(String.valueOf(num));
            }
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
