package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14890 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int l;
    private static int[][] board;

    private static final int[] dy = {0, 1};
    private static final int[] dx = {1, 0};

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; ++i) {
            func(i, true);
            func(i, false);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, boolean isRow) {
        int dir = isRow ? 0 : 1;
        int y = isRow ? idx : 0;
        int x = isRow ? 0 : idx;

        int cnt = 1;
        boolean down = false;
        boolean possible = true;
        for (int i = 1; i < n; ++i) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (Math.abs(board[y][x] - board[ny][nx]) > 1) {
                possible = false;
                break;
            }

            if (board[y][x] == board[ny][nx]) {
                y = ny;
                x = nx;
                cnt++;
                continue;
            }

            boolean nowDown = board[y][x] > board[ny][nx];

            if (down == nowDown) {
                if (cnt < l) {
                    possible = false;
                    break;
                }
            } else if (down && cnt < 2 * l) {
                possible = false;
                break;
            }

            down = nowDown;
            cnt = 1;
            y = ny;
            x = nx;
        }

        if (possible) {
            if (down && cnt < l) {
                return;
            }

            ans++;
        }
    }

}
