package barkingdog.chap07_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B4949 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String sentence = br.readLine();
            if (sentence.equals(".")) {
                break;
            }

            Stack<Character> stk = new Stack<>();
            boolean isBalanced = true;
            Loop:
            for (int i = 0; i < sentence.length(); ++i) {
                char c = sentence.charAt(i);
                switch (c) {
                    case '(':
                    case '[':
                        stk.push(c);
                        break;
                    case ')':
                        if (stk.isEmpty() || stk.peek() != '(') {
                            isBalanced = false;
                            break Loop;
                        }
                        stk.pop();
                        break;
                    case ']':
                        if (stk.isEmpty() || stk.peek() != '[') {
                            isBalanced = false;
                            break Loop;
                        }
                        stk.pop();
                        break;
                    default:
                        break;
                }
            }

            if (isBalanced && stk.isEmpty()) {
                bw.write("yes");
            } else {
                bw.write("no");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
