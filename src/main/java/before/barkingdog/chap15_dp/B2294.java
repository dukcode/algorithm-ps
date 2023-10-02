package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2294 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int k;
    private static int[] coins;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        k = Integer.parseInt(st.nextToken());
        cache = new int[k + 1];

        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[0] = 0;
        for (int i = 0; i < n; ++i) {
            int coin = coins[i];
            for (int j = coin; j <= k; ++j) {
                if (cache[j - coin] != Integer.MAX_VALUE) {
                    cache[j] = Math.min(cache[j], cache[j - coin] + 1);
                }

            }
        }

        if (cache[k] == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(cache[k]));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
