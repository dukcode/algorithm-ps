package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9251 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static String w1;
    private static String w2;

    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        w1 = br.readLine();
        w2 = br.readLine();

        cache = new int[w2.length() + 1][w1.length() + 1];
        for (int y = 1; y <= w2.length(); ++y) {
            for (int x = 1; x <= w1.length(); ++x) {
                if (w1.charAt(x - 1) == w2.charAt(y - 1)) {
                    cache[y][x] = cache[y - 1][x - 1] + 1;
                    continue;
                }

                cache[y][x] = Math.max(cache[y - 1][x], cache[y][x - 1]);
            }
        }

        bw.write(String.valueOf(cache[w2.length()][w1.length()]));

        bw.flush();
        bw.close();
        br.close();
    }

}
