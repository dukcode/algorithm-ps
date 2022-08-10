package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15655 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int m;

    private static int[] arr;
    private static int[] ans;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        m = Integer.parseInt(st.nextToken());
        ans = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        func(-1, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    /*
    lastIdx : 지금까지 ans에서 가장 큰 값의 arr에서의 위치
    idx : 결정할 ans의 idx 위치
     */

    private static void func(int lastIdx, int idx) throws IOException {
        if (idx == m) {
            for (int i = 0; i < m; ++i) {
                bw.write(String.valueOf(ans[i]));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        for (int i = lastIdx + 1; i < n; ++i) {
            int num = arr[i];
            ans[idx] = num;
            func(i, idx + 1);
        }
    }
}
