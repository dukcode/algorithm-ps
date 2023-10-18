package jongman.chap08_dynamic_programming;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantization {
    private static final int INF = 987_654_321;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int c;
    private static int n;
    private static int s;
    private static int[] arr;

    private static int[][] cache;

    private static int[] psum;
    private static int[] sqPsum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            cache = new int[n][s];
            for (int y = 0; y < n; ++y) {
                Arrays.fill(cache[y], INF);
            }

            preCalc();

            bw.write(String.valueOf(quantize(0, s)));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void preCalc() {
        psum = new int[n + 1];
        sqPsum = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            psum[i] = psum[i - 1] + arr[i - 1];
            sqPsum[i] = sqPsum[i - 1] + (arr[i - 1] * arr[i - 1]);
        }
    }

    private static int quantize(int fromIdx, int parts) {
        if (fromIdx == n) {
            return 0;
        }

        if (parts == 0) {
            return INF;
        }

        if (cache[fromIdx][parts - 1] != INF) {
            return cache[fromIdx][parts - 1];
        }

        for (int toIdx = fromIdx; toIdx < n; ++toIdx) {
            cache[fromIdx][parts - 1] = Math.min(cache[fromIdx][parts - 1],
                    minError(fromIdx, toIdx) + quantize(toIdx + 1, parts - 1));
        }

        return cache[fromIdx][parts - 1];
    }

    private static int minError(int fromIdx, int toIdx) {
        int sum = psum[toIdx + 1] - psum[fromIdx];
        int sqSum = sqPsum[toIdx + 1] - sqPsum[fromIdx];
        int cnt = toIdx - fromIdx + 1;

        int m = (int) (0.5 + ((double) sum / cnt));

        return sqSum - 2 * m * sum + cnt * m * m;
    }

}
