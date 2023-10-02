package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B15988 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int t;
    private static int n;

    private static long[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        cache = new long[1_000_001];
        Arrays.fill(cache, -1);
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;

        int idx = 2;
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            if (cache[n] != -1) {
                bw.write(String.valueOf(cache[n]));
                bw.newLine();
                continue;
            }

            for (int i = idx + 1; i <= n; ++i) {
                cache[i] = (cache[i - 1] + cache[i - 2] + cache[i - 3]) % 1_000_000_009L;
            }
            bw.write(String.valueOf(cache[n]));
            bw.newLine();
            idx = n;
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
