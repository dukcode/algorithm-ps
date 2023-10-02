package before.review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B6198 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    private static long ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        Stack<Integer> stk = new Stack<>();
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());

            while (!stk.isEmpty() && stk.peek() <= num) {
                stk.pop();
            }

            ans += stk.size();
            stk.push(num);
        }

        bw.write(String.valueOf(ans));

        bw.close();
        br.close();
    }
}
