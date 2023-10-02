package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14442 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int k;

    private static int[][] board;
    private static int[][][] dist;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};

    private static int ans = Integer.MAX_VALUE;

    private static class Point {

        int y;
        int x;
        int k;

        public Point(int y, int x, int k) {
            this.y = y;
            this.x = x;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[k + 1][h][w];
        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                board[y][x] = line.charAt(x) - '0';
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, k));
        dist[k][0][0] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny == h - 1 && nx == w - 1) {
                    bw.write(String.valueOf(dist[cur.k][cur.y][cur.x] + 1));
                    bw.close();
                    br.close();
                    return;
                }

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] != 0 && cur.k == 0) {
                    continue;
                }

                if (board[ny][nx] == 0) {
                    if (dist[cur.k][ny][nx] != 0) {
                        continue;
                    }

                    dist[cur.k][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx, cur.k));
                } else {
                    if (dist[cur.k - 1][ny][nx] != 0) {
                        continue;
                    }

                    dist[cur.k - 1][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx, cur.k - 1));
                }
            }
        }

        for (int i = 0; i <= k; i++) {
            if (dist[i][h - 1][w - 1] == 0) {
                continue;
            }
            ans = Math.min(ans, dist[i][h - 1][w - 1]);
        }
        ans = ans == Integer.MAX_VALUE ? -1 : ans;

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
