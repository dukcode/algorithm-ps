package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10942 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;

    private static int m;
    private static int s;
    private static int e;

    private static boolean[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        cache = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(cache[i], true);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int start = n - 1; start >= 0; --start) {
            for (int end = start + 1; end < n; ++end) {

                if (arr[start] != arr[end]) {
                    cache[start][end] = false;
                    continue;
                }

                cache[start][end] = cache[start + 1][end - 1];
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(cache[s - 1][e - 1] ? 1 : 0));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
