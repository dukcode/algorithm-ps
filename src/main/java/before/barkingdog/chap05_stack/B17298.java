package before.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class B17298 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] ans = new int[N];
        Stack<Integer> stk = new Stack<>();
        for (int i = N - 1; i >= 0; --i) {

            while (!stk.isEmpty() && arr[i] >= stk.peek()) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stk.peek();
            }

            stk.push(arr[i]);
        }

        for (int i : ans) {
            bw.write(String.valueOf(i));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
