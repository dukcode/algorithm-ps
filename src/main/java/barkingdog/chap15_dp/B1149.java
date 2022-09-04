package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1149 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] house;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        house = new int[n][3];
        cache = new int[n][3];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        cache[0][0] = house[0][0];
        cache[0][1] = house[0][1];
        cache[0][2] = house[0][2];

        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j < 3; ++j) {
                    cache[i][j] = house[i][j]
                            + Math.min(cache[i - 1][(j + 1) % 3], cache[i - 1][(j + 2) % 3]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            ans = Math.min(ans, cache[n - 1][i]);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
