package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2193 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static long[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        cache = new long[n + 1][2];

        cache[1][0] = 0;
        cache[1][1] = 1;

        for (int i = 2; i <= n; ++i) {
            cache[i][0] = cache[i - 1][1] + cache[i - 1][0];
            cache[i][1] = cache[i - 1][0];
        }
        bw.write(String.valueOf(cache[n][0] + cache[n][1]));

        bw.flush();
        bw.close();
        br.close();
    }

}
