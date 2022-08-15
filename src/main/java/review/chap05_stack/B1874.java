package review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B1874 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int i = 1;
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());

            while (stk.isEmpty() || stk.peek() < num) {
                stk.push(i);
                i++;
                sb.append("+\n");
            }

            if (stk.peek() == num) {
                stk.pop();
                sb.append("-\n");
            } else {
                sb = new StringBuilder("NO");
                break;
            }

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}
