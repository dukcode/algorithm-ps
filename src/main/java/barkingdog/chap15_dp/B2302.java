package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B2302 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int m;

    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        cache = new int[n + 1];
        Arrays.fill(cache, 1);
        for (int i = 2; i <= n; ++i) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        int before = 0;
        int ans = 1;
        for (int i = 0; i < m; ++i) {
            int now = Integer.parseInt(br.readLine());
            ans *= cache[now - before - 1];
            before = now;
        }

        ans *= cache[n - before];

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
