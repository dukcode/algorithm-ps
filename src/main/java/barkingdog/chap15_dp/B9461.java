package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9461 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int t;

    private static long[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cache = new long[101];
        cache[1] = 1L;
        cache[2] = 1L;
        for (int i = 3; i < 101; ++i) {
            cache[i] = cache[i - 2] + cache[i - 3];
        }

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int num = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(cache[num]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
