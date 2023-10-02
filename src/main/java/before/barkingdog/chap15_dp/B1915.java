package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1915 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;

    private static char[][] board;
    private static int[][] cache;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new char[h][];
        for (int y = 0; y < h; ++y) {
            board[y] = br.readLine().toCharArray();
        }

        cache = new int[h + 1][w + 1];
        for (int y = 1; y <= h; ++y) {
            for (int x = 1; x <= w; ++x) {
                if (board[y - 1][x - 1] == '0') {
                    continue;
                }

                cache[y][x] = Math.min(Math.min(cache[y - 1][x], cache[y][x - 1]),
                        cache[y - 1][x - 1]) + 1;

                ans = Math.max(ans, cache[y][x] * cache[y][x]);
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
