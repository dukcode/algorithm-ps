package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2133 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        cache = new int[4][n + 1];  // 다음 열을 몇개 채우는지
        cache[0][0] = 1;
        cache[0][1] = 0;
        cache[1][1] = 2;
        cache[2][1] = 0;
        cache[3][1] = 1;

        for (int i = 2; i <= n; ++i) {
            cache[0][i] = cache[1][i - 1];
            cache[1][i] = cache[2][i - 1] + cache[0][i - 1] * 2 + cache[3][i - 2] * 2;
            cache[2][i] = cache[1][i - 1];
            cache[3][i] = cache[0][i - 1] + cache[3][i - 2];
        }

        bw.write(String.valueOf(cache[3][n - 1] + cache[0][n]));

        bw.flush();
        bw.close();
        br.close();
    }

}
