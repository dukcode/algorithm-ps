package before.review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B17298_2 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] arr;
    private static int[] ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ans = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; --i) {
            int idx = i + 1;

            while (idx < n && arr[i] >= arr[idx]) {
                idx = ans[idx];
            }

            ans[i] = idx;
        }

        for (int i = 0; i < n; ++i) {
            bw.write(String.valueOf(ans[i] == n ? -1 : arr[ans[i]]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
