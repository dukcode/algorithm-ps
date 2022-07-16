package barkingdog.chap07_stack_pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B9012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String str = br.readLine();

            Stack<Character> stk = new Stack<>();
            boolean isBalanced = true;
            for (int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);

                if (c == ')' && (!stk.isEmpty() && stk.peek() == '(')) {
                    stk.pop();
                } else if (c == '(') {
                    stk.push(c);
                } else {
                    isBalanced = false;
                    break;
                }
            }

            if (isBalanced && stk.isEmpty()) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
