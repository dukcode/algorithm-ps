package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2193_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static long[] cache = new long[91];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        cache[1] = 1;
        cache[2] = 1;

        for (int i = 3; i <= n; ++i) {
            cache[i] = cache[i - 2] + cache[i - 2];
        }
        bw.write(String.valueOf(cache[n]));

        bw.flush();
        bw.close();
        br.close();
    }

}
