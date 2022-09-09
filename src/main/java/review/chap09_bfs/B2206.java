package review.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;

    private static int[][] board;
    private static int[][][] dist;

    private static class Point {

        int y;
        int x;
        boolean crashed;

        public Point(int y, int x, boolean crashed) {
            this.y = y;
            this.x = x;
            this.crashed = crashed;
        }
    }

    private static int[] dy = {0, 0, 1, -1};
    private static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        dist = new int[2][h][w];

        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                int block = line.charAt(x) - '0';
                board[y][x] = block;
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, false));
        dist[1][0][0] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                boolean crashed = cur.crashed;

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    if (!crashed && dist[0][ny][nx] == 0) {
                        dist[0][ny][nx] = dist[1][cur.y][cur.x] + 1;
                        q.offer(new Point(ny, nx, true));
                    }
                } else {
                    if (crashed) {
                        if (dist[0][ny][nx] == 0) {
                            dist[0][ny][nx] = dist[0][cur.y][cur.x] + 1;
                            q.offer(new Point(ny, nx, true));
                        }
                    } else {
                        if (dist[1][ny][nx] == 0) {
                            dist[1][ny][nx] = dist[1][cur.y][cur.x] + 1;
                            q.offer(new Point(ny, nx, false));
                        }
                    }
                }


            }
        }

        if (dist[1][h - 1][w - 1] == 0 && dist[0][h - 1][w - 1] == 0) {
            bw.write(String.valueOf(-1));
        } else if (dist[1][h - 1][w - 1] == 0) {
            bw.write(String.valueOf(dist[0][h - 1][w - 1]));
        } else if (dist[0][h - 1][w - 1] == 0) {
            bw.write(String.valueOf(dist[1][h - 1][w - 1]));
        } else {
            bw.write(String.valueOf(Math.min(dist[1][h - 1][w - 1], dist[0][h - 1][w - 1])));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
