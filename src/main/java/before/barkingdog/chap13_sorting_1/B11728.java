package before.barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B11728 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static int[] arrA;
    private static int[] arrB;

    private static int[] ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arrA = new int[n];
        arrB = new int[m];
        ans = new int[n + m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int a = 0;
        int b = 0;
        while (a <= n && b <= m) {
            if (a == n && b == m) {
                break;
            }

            if (a == n) {
                ans[idx] = arrB[b];
                idx++;
                b++;
                continue;
            }

            if (b == m) {
                ans[idx] = arrA[a];
                idx++;
                a++;
                continue;
            }

            if (arrA[a] < arrB[b]) {
                ans[idx] = arrA[a];
                a++;
            } else {
                ans[idx] = arrB[b];
                b++;
            }
            idx++;
        }

        for (int i = 0; i < n + m; ++i) {
            bw.write(String.valueOf(ans[i]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
