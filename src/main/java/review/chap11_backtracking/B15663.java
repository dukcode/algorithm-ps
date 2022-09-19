package review.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15663 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int[] arr;
    private static boolean[] taken;

    private static int[] ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        taken = new boolean[n];
        ans = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        func(0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx) throws IOException {
        if (idx == m) {
            for (int i = 0; i < m; ++i) {
                bw.write(String.valueOf(ans[i]));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        ans[idx] = 0;
        for (int i = 0; i < n; ++i) {
            if (ans[idx] == arr[i]) {
                continue;
            }

            if (taken[i]) {
                continue;
            }

            ans[idx] = arr[i];

            taken[i] = true;
            func(idx + 1);
            taken[i] = false;
        }
    }

}
