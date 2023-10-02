package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1463 {

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

        for (int i = 1; i <= n; ++i) {
            if (i + 1 <= n) {
                cache[i + 1] =
                        cache[i + 1] == 0 ? cache[i] + 1 : Math.min(cache[i + 1], cache[i] + 1);
            }

            if (i * 2 <= n) {
                cache[i * 2] =
                        cache[i * 2] == 0 ? cache[i] + 1 : Math.min(cache[i * 2], cache[i] + 1);
            }

            if (i * 3 <= n) {
                cache[i * 3] =
                        cache[i * 3] == 0 ? cache[i] + 1 : Math.min(cache[i * 3], cache[i] + 1);
            }
        }

        bw.write(String.valueOf(cache[n]));

        bw.flush();
        bw.close();
        br.close();
    }

}
