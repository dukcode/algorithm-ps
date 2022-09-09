package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14501 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static int[] t;
    private static int[] p;

    private static int[] cache;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];

        cache = new int[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; ++i) {
            int today = i + t[i] <= n ? p[i] : 0;
            cache[i] = today;
            for (int j = 0; j < n; ++j) {
                if (t[j] + j <= i) {
                    cache[i] = Math.max(cache[i], today + cache[j]);
                }
            }

            ans = Math.max(ans, cache[i]);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
