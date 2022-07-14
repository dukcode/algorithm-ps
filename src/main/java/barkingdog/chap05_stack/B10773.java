package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B10773 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        while (K-- > 0) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        int ans = 0;
        for (Integer num : stack) {
            ans += num;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }


}
