package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2178 {

    static int h;
    static int w;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] board;
    static int[][] dist;

    static int cnt;

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean equals(Point p) {
            if (this == p) {
                return true;
            }

            if (p == null) {
                return false;
            }

            return y == p.y && x == p.x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st1.nextToken());
        w = Integer.parseInt(st1.nextToken());

        board = new int[h][w];
        dist = new int[h][w];

        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                board[y][x] = line.charAt(x) - '0';
                Arrays.fill(dist[y], -1);
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        dist[0][0] = 1;

        Loop:
        while (true) {
            Point cur = q.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == 0 || dist[ny][nx] != -1) {
                    continue;
                }

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                if (ny == h - 1 && nx == w - 1) {
                    break Loop;
                }

                q.offer(new Point(ny, nx));
            }
        }

        bw.write(String.valueOf(dist[h - 1][w - 1]));

        bw.flush();
        bw.close();
        br.close();
    }

}
