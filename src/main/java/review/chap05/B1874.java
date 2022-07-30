package review.chap05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B1874 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int count = 1;
        Stack<Integer> stk = new Stack<>();
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());

            while (stk.isEmpty() || stk.peek() < num) {
                stk.push(count++);
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
