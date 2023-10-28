package jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AsymTiling1 {
    private static final int MOD = 1_000_000_007;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int c;
    private static int n;

    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            n = Integer.parseInt(br.readLine());

            cache = new int[n + 1];
            bw.write(String.valueOf(asymTiling(n)));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static int asymTiling(int width) {
        if (width % 2 == 1) {
            return (tiling(width) - tiling(width / 2) + MOD) % MOD;
        }

        int ret = tiling(width);
        ret = (ret - tiling(width / 2) + MOD) % MOD;
        ret = (ret - tiling(width / 2 - 1) + MOD) % MOD;
        return ret;
    }

    private static int tiling(int width) {
        if (width == 0) {
            return 1;
        }

        if (width <= 2) {
            return width;
        }

        if (cache[width] != 0) {
            return cache[width];
        }

        return cache[width] = (tiling(width - 1) + tiling(width - 2)) % MOD;
    }


}
