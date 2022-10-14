package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B11057 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[][] cache;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        cache = new int[10][n + 1];
        cache[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int sum = 0;
            for (int j = 0; j <= 9; ++j) {
                sum += cache[j][i - 1];
                sum %= 10007;
                cache[j][i] = sum;
            }
        }

        for (int i = 0; i <= 9; ++i) {
            ans += cache[i][n];
        }
        ans %= 10007;

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
