package before.review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298 {

    static int[] arr;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ans = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stk = new Stack<>();
        for (int i = n - 1; i >= 0; --i) {
            int idx = i + 1;

            while (idx < n && arr[i] >= arr[idx]) {
                idx = ans[idx];
            }
            ans[i] = idx;
        }

        for (int i = 0; i < n; ++i) {
            int idx = ans[i];
            bw.write(String.valueOf(idx == n ? -1 : arr[idx]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
