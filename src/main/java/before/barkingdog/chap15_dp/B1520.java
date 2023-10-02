package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1520 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;

    private static int[][] board;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        cache = new int[h][w];
        for (int y = 0; y < h; ++y) {
            Arrays.fill(cache[y], -1);
        }

        bw.write(String.valueOf(func(h - 1, w - 1)));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int func(int y, int x) {

        if (cache[y][x] != -1) {
            return cache[y][x];
        }

        if (y == 0 && x == 0) {
            return 1;
        }

        int ret = 0;
        if (y + 1 < h && board[y][x] < board[y + 1][x]) {
            ret += func(y + 1, x);
        }

        if (y - 1 >= 0 && board[y][x] < board[y - 1][x]) {
            ret += func(y - 1, x);
        }

        if (x + 1 < w && board[y][x] < board[y][x + 1]) {
            ret += func(y, x + 1);
        }

        if (x - 1 >= 0 && board[y][x] < board[y][x - 1]) {
            ret += func(y, x - 1);
        }

        cache[y][x] = ret;
        return ret;
    }

}
