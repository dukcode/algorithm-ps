package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B10844 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_000;

    private static int n;
    private static long[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cache = new long[101][10];
        for (int i = 1; i <= 9; ++i) {
            cache[1][i] = 1;
        }

        n = Integer.parseInt(br.readLine());
        for (int i = 2; i <= n; ++i) {
            cache[i][0] = cache[i - 1][1];
            cache[i][9] = cache[i - 1][8];
            for (int j = 1; j <= 8; ++j) {
                cache[i][j] = (cache[i - 1][j - 1] + cache[i - 1][j + 1]) % MOD;
            }
        }

        bw.write(String.valueOf(Arrays.stream(cache[n]).sum() % MOD));

        bw.flush();
        bw.close();
        br.close();
    }

}
