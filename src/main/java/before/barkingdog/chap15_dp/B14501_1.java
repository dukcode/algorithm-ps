package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14501_1 {

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
        for (int i = n - 1; i >= 0; --i) {
            if (t[i] + i <= n) {
                cache[i] = cache[i + t[i]] + p[i];
            }

            cache[i] = Math.max(cache[i], cache[i + 1]);
        }

        bw.write(String.valueOf(cache[0]));

        bw.flush();
        bw.close();
        br.close();
    }

}
