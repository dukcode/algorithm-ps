package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15486 {

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
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];

        cache = new int[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                cache[i] = Math.max(cache[i], cache[i - 1]);
            }

            if (i + t[i] <= n) {
                cache[i + t[i]] = Math.max(cache[i + t[i]], cache[i] + p[i]);
            }
        }

        bw.write(String.valueOf(Math.max(cache[n - 1], cache[n])));

        bw.close();
        br.close();
    }

}
