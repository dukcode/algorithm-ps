package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14889 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static boolean[] taken;
    private static int[][] arr;

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        taken = new boolean[n];
        arr = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        func(0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx) {
        if (idx == n / 2) {
            ans = Math.min(getScoreInterval(), ans);
            return;
        }

        for (int i = startIdx; i < n; ++i) {
            if (taken[i]) {
                continue;
            }

            taken[i] = true;
            func(idx + 1, i + 1);
            taken[i] = false;
        }
    }

    private static int getScoreInterval() {

        int aScore = 0;
        int bScore = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (taken[i] && taken[j]) {
                    aScore += arr[i][j];
                }
                if (!taken[i] && !taken[j]) {
                    bScore += arr[i][j];
                }
            }
        }
        return Math.abs(aScore - bScore);
    }

}
