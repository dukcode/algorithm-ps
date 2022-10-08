package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15685 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static int x;
    private static int y;
    private static int d;
    private static int g;

    private static boolean[][] board;

    private static final int[] dy = {0, -1, 0, 1};
    private static final int[] dx = {1, 0, -1, 0};

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new boolean[101][101];
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            List<Integer> dirs = new ArrayList<>();
            dirs.add(d);

            board[y][x] = true;

            while (g-- > 0) {
                int size = dirs.size();
                for (int i = size - 1; i >= 0; --i) {
                    dirs.add((dirs.get(i) + 1) % 4);
                }
            }

            for (Integer dir : dirs) {
                y += dy[dir];
                x += dx[dir];

                if (y < 0 || y > 100 || x < 0 || x > 100) {
                    continue;
                }

                board[y][x] = true;
            }

        }

        for (int y = 0; y < 100; ++y) {
            for (int x = 0; x < 100; ++x) {
                if (board[y][x] && board[y + 1][x] &&
                        board[y + 1][x + 1] && board[y][x + 1]) {
                    ans++;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}
