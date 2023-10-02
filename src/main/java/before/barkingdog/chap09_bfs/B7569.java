package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7569 {

    static int m;
    static int n;
    static int h;

    static int[] dz = {1, 0, 0, -1, 0, 0};
    static int[] dy = {0, 1, 0, 0, -1, 0};
    static int[] dx = {0, 0, 1, 0, 0, -1};

    static int[][][] board;

    static class Point {

        int z;
        int y;
        int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st1.nextToken());
        n = Integer.parseInt(st1.nextToken());
        h = Integer.parseInt(st1.nextToken());

        board = new int[h][n][m];

        Queue<Point> q = new LinkedList<>();
        for (int z = 0; z < h; ++z) {
            for (int y = 0; y < n; ++y) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int x = 0; x < m; ++x) {
                    int tomato = Integer.parseInt(st2.nextToken());
                    if (tomato == 1) {
                        q.offer(new Point(z, y, x));
                    }
                    board[z][y][x] = tomato;
                }
            }
        }

        int cnt = 1;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 6; ++i) {
                int nz = cur.z + dz[i];
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (nz < 0 || nz >= h || ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }

                if (board[nz][ny][nx] != 0) {
                    continue;
                }

                cnt = board[nz][ny][nx] = board[cur.z][cur.y][cur.x] + 1;
                q.offer(new Point(nz, ny, nx));
            }
        }

        Loop:
        for (int z = 0; z < h; ++z) {
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < m; ++x) {
                    if (board[z][y][x] == 0) {
                        cnt = 0;
                        break Loop;
                    }
                }
            }
        }

        bw.write(String.valueOf(cnt - 1));

        bw.flush();
        bw.close();
        br.close();
    }

}
