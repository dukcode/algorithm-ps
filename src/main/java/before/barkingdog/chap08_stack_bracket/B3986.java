package before.barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B3986 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int ans = 0;
        while (n-- > 0) {
            String str = br.readLine();

            Stack<Character> stk = new Stack<>();
            for (int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);

                if (stk.isEmpty() || stk.peek() != c) {
                    stk.push(c);
                    continue;
                }
                stk.pop();
            }

            ans += stk.isEmpty() ? 1 : 0;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }


}
