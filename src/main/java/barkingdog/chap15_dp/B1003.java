package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1003 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int t;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cache = new int[2][41];
        cache[0][0] = 1;
        cache[1][0] = 0;
        cache[0][1] = 0;
        cache[1][1] = 1;

        for (int i = 2; i <= 40; ++i) {
            cache[0][i] = cache[0][i - 1] + cache[0][i - 2];
            cache[1][i] = cache[1][i - 1] + cache[1][i - 2];
        }

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int num = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(cache[0][num]));
            bw.write(' ');
            bw.write(String.valueOf(cache[1][num]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
