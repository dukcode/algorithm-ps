package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9095 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int t;
    private static int n;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cache = new int[12];
        cache[0] = 1;
        for (int i = 0; i < 12; ++i) {
            if (i - 3 >= 0) {
                cache[i] += cache[i - 3];
            }
            if (i - 2 >= 0) {
                cache[i] += cache[i - 2];
            }
            if (i - 1 >= 0) {
                cache[i] += cache[i - 1];
            }

        }

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(cache[n]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
