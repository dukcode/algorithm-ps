package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15663 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int m;

    private static int[] arr;
    private static boolean[] isUsed;
    private static int[] ans;


    public static void main(String[] args) throws IOException {

        input();
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

        int num = 0;
        for (int i = 0; i < n; ++i) {
            if (isUsed[i]) {
                continue;
            }

            if (num == arr[i]) {
                continue;
            }

            num = arr[i];
            ans[idx] = num;
            isUsed[i] = true;
            func(idx + 1);
            isUsed[i] = false;
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        isUsed = new boolean[n];
        ans = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

}
