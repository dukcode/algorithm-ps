package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1463_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        cache = new int[n + 1];

        for (int i = 2; i <= n; ++i) {
            cache[i] = cache[i - 1] + 1;
            if (i % 2 == 0) {
                cache[i] = Math.min(cache[i], cache[i / 2] + 1);
            }
            if (i % 3 == 0) {
                cache[i] = Math.min(cache[i], cache[i / 3] + 1);
            }
        }

        bw.write(String.valueOf(cache[n]));

        bw.flush();
        bw.close();
        br.close();

    }
}
