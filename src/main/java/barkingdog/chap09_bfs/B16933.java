package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16933 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int k;
    private static char[][] board;

    private static int[][][][] dist;    // k, d/n, y, x

    private static final int DAY = 0;
    private static final int NIGHT = 1;

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {-1, 1, 0, 0};

    private static class Point {

        int y;
        int x;
        int k;
        int status;

        public Point(int y, int x, int k, int status) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.status = status;
        }
    }

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[h][w];
        for (int y = 0; y < h; ++y) {
            board[y] = br.readLine().toCharArray();
        }

        dist = new int[k + 1][2][h][w];

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, k, DAY));
        dist[k][DAY][0][0] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int nextStatus = cur.status == DAY ? NIGHT : DAY;
            int nextDist = dist[cur.k][cur.status][cur.y][cur.x] + 1;

            if (cur.y == h - 1 && cur.x == w - 1) {
                ans = dist[cur.k][cur.status][cur.y][cur.x];
                break;
            }

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == '1') {
                    if (cur.k == 0) {
                        continue;
                    }

                    if (cur.status == DAY) {
                        if (dist[cur.k - 1][NIGHT][ny][nx] != 0) {
                            continue;
                        }

                        dist[cur.k - 1][NIGHT][ny][nx] = nextDist;
                        q.offer(new Point(ny, nx, cur.k - 1, NIGHT));
                    } else {
                        if (dist[cur.k][DAY][cur.y][cur.x] != 0) {
                            continue;
                        }

                        dist[cur.k][nextStatus][cur.y][cur.x] = nextDist;
                        q.offer(new Point(cur.y, cur.x, cur.k, nextStatus));
                    }

                } else {
                    if (dist[cur.k][nextStatus][ny][nx] != 0) {
                        continue;
                    }

                    dist[cur.k][nextStatus][ny][nx] = nextDist;
                    q.offer(new Point(ny, nx, cur.k, nextStatus));
                }
            }


        }

        ans = ans == 0 ? -1 : ans;
        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
