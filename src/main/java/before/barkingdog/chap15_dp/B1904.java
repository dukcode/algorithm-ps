package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1904 {

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
        cache[0] = 1;
        cache[1] = 1;

        for (int i = 2; i <= n; ++i) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % 15746;
        }

        bw.write(String.valueOf(cache[n]));

        bw.flush();
        bw.close();
        br.close();
    }

}
