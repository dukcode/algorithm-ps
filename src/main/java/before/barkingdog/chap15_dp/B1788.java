package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1788 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] cache;
    private static boolean isNegative;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        if (n < 0) {
            isNegative = true;
        }

        int num = Math.abs(n);
        if (n == 0) {
            bw.write(String.valueOf(0));
        } else if (isNegative && n % 2 == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(1));
        }
        bw.newLine();

        bw.write(String.valueOf(func(num)));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int func(int n) {
        if (0 <= n && n <= 1) {
            return n;
        }

        cache = new int[n + 1];
        cache[1] = 1;
        for (int i = 2; i <= n; ++i) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % 1_000_000_000;
        }

        return cache[n];
    }

}
