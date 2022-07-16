package barkingdog.chap07_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        boolean isBalanced = true;
        int ans = 0;
        int count = 1;
        Stack<Character> stk = new Stack<>();
        Loop:
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '(':
                case '[':
                    stk.push(c);
                    count *= c == '(' ? 2 : 3;
                    break;
                case ')':
                case ']':
                    char counter = c == ')' ? '(' : '[';
                    int product = c == ')' ? 2 : 3;

                    if (stk.isEmpty() || stk.peek() != counter) {
                        isBalanced = false;
                        break Loop;
                    }

                    if (str.charAt(i - 1) == counter) {
                        ans += count;
                    }
                    count /= product;
                    stk.pop();

                    break;
                default:
                    break;
            }
        }

        if (!stk.isEmpty() || !isBalanced) {
            bw.write("0");
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
