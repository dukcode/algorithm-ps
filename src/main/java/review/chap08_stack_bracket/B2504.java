package review.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B2504 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static Stack<Character> stk = new Stack<>();
    private static boolean isBalanced = true;
    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        int count = 1;
        Loop:
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            switch (c) {
                case '(':
                case '[':
                    count *= c == '(' ? 2 : 3;
                    stk.push(c);
                    break;
                case ')':
                case ']':
                    char counter = c == ')' ? '(' : '[';
                    int product = c == ')' ? 2 : 3;

                    if (stk.isEmpty() || stk.peek() != counter) {
                        isBalanced = false;
                        break Loop;
                    }

                    if (line.charAt(i - 1) == counter) {
                        ans += count;
                    }
                    count /= product;
                    stk.pop();
                    break;
                default:
                    break;
            }
        }
        if (isBalanced && stk.isEmpty()) {
            bw.write(String.valueOf(ans));
        } else {
            bw.write('0');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
