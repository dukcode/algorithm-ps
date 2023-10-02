package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600 {

    static int[] dyHorse = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dxHorse = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dyMonkey = new int[]{1, 0, -1, 0};
    static int[] dxMonkey = new int[]{0, 1, 0, -1};

    static int[][] board;
    static int[][][] dist;

    static int count;
    static int w;
    static int h;

    static class Point {

        int y;
        int x;
        int count;

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        dist = new int[count + 1][h][w];

        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> q = new LinkedList<>();
        dist[count][0][0] = 1;
        q.offer(new Point(0, 0, count));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.count > 0) {
                for (int i = 0; i < 8; ++i) {
                    int ny = cur.y + dyHorse[i];
                    int nx = cur.x + dxHorse[i];

                    if (!inRange(ny, nx)) {
                        continue;
                    }

                    if (board[ny][nx] == 1) {
                        continue;
                    }

                    if (dist[cur.count - 1][ny][nx] != 0
                            && dist[cur.count - 1][ny][nx] <= dist[cur.count][cur.y][cur.x] + 1) {
                        continue;
                    }

                    dist[cur.count - 1][ny][nx] = dist[cur.count][cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx, cur.count - 1));

                }
            }

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dyMonkey[i];
                int nx = cur.x + dxMonkey[i];

                if (!inRange(ny, nx)) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    continue;
                }

                if (dist[cur.count][ny][nx] != 0
                        && dist[cur.count][ny][nx] <= dist[cur.count][cur.y][cur.x] + 1) {
                    continue;
                }

                dist[cur.count][ny][nx] = dist[cur.count][cur.y][cur.x] + 1;
                q.offer(new Point(ny, nx, cur.count));
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= count; ++i) {
            if (dist[i][h - 1][w - 1] != 0) {
                ans = Math.min(ans, dist[i][h - 1][w - 1]);
            }
        }

        if (ans == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans - 1));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < h && x >= 0 && x < w;
    }
}
