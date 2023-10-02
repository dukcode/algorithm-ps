package before.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int count = 1;
        while (N-- > 0) {
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
