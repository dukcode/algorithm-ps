package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1799 {


    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[][] board;
    private static boolean[] isUsed;

    private static int maxBlack = 0;
    private static int maxWhite = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        funcBlack(0, 0);
        funcBlack(1, 0);
        bw.write(String.valueOf(maxBlack + maxWhite));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void funcBlack(int sum, int count) {
        if (sum == 2 * n) {
            maxBlack = Math.max(maxBlack, count);
            return;
        }

        if (sum == 2 * n - 1) {
            maxWhite = Math.max(maxWhite, count);
            return;
        }

        int start = sum > n - 1 ? sum - (n - 1) : 0;
        int end = sum - start;

        for (int x = start; x <= end; ++x) {
            int y = sum - x;

            if (board[y][x] == 0) {
                continue;
            }

            if (isUsed[y - x + n]) {
                continue;
            }

            isUsed[y - x + n] = true;
            funcBlack(sum + 2, count + 1);
            isUsed[y - x + n] = false;
        }

        funcBlack(sum + 2, count);
    }

    private static void getInput() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        StringTokenizer st;
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        isUsed = new boolean[2 * n + 1];
    }

}
