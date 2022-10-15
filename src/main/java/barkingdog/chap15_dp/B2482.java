package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2482 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int k;

    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        cache = new int[1005][1005];
        for (int y = 1; y <= n; ++y) {
            cache[y][1] = y;
        }

        for (int y = 4; y <= n; ++y) {
            for (int x = 2; x <= y / 2; ++x) {
                cache[y][x] = (cache[y - 2][x - 1] + cache[y - 1][x]) % 1_000_000_003;
            }
        }

        bw.write(String.valueOf(cache[n][k]));

        bw.flush();
        bw.close();
        br.close();
    }

}
