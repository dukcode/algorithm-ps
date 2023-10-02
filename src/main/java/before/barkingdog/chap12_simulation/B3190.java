package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B3190 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int k;

    private static int l;
    private static int second;
    private static int rot;

    private static int[][] board;
    private static int[][] dirs;

    private static int y;
    private static int x;

    // X, 오른쪽, 아래, 왼쪽, 위
    private static int[] dy = {0, 0, 1, 0, -1};
    private static int[] dx = {0, 1, 0, -1, 0};

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dirs = new int[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            board[y][x] = -1;
        }

        y = 0;
        x = 0;
        board[0][0] = 1;
        dirs[0][0] = 1;
        Point end = new Point(0, 0);
        int time = 0;

        l = Integer.parseInt(br.readLine());
        int dir = dirs[y][x];

        Loop:
        for (int i = 0; i <= l; i++) {
            if (i != l) {
                st = new StringTokenizer(br.readLine());
                second = Integer.parseInt(st.nextToken());
                rot = st.nextToken().charAt(0) == 'L' ? 3 : 1;
            } else {
                second = Integer.MAX_VALUE;
                rot = 0;
            }

            for (int t = time; t < second; ++t) {

                time++;
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    break Loop;
                }

                // 자신과 부딛힘
                if (board[ny][nx] == 1) {
                    break Loop;
                }

                if (board[ny][nx] == -1) {
                    dirs[y][x] = dir;
                    board[ny][nx] = 1;
                    dirs[ny][nx] = dir;
                    y = ny;
                    x = nx;
                    continue;
                }

                board[ny][nx] = 1;
                dirs[y][x] = dir;
                dirs[ny][nx] = dir;
                int endDir = dirs[end.y][end.x];
                board[end.y][end.x] = 0;
                dirs[end.y][end.x] = 0;
                end.y = end.y + dy[endDir];
                end.x = end.x + dx[endDir];
                y = ny;
                x = nx;

            }

            dir = (dirs[y][x] + rot - 1) % 4 + 1;
        }

        bw.write(String.valueOf(time));

        bw.flush();
        bw.close();
        br.close();
    }

}
