package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {

    static int h;
    static int w;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] board;

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st1.nextToken());
        h = Integer.parseInt(st1.nextToken());

        board = new int[h][w];

        Queue<Point> q = new LinkedList<>();
        for (int y = 0; y < h; ++y) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st2.nextToken());
                if (board[y][x] == 1) {
                    q.offer(new Point(y, x));
                }
            }
        }

        int maxCount = 0;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] != 0) {
                    continue;
                }

                q.offer(new Point(ny, nx));
                board[ny][nx] = board[cur.y][cur.x] + 1;
                maxCount = board[ny][nx] - 1;
            }
        }

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 0) {
                    maxCount = -1;
                    break;
                }
            }
        }

        bw.write(String.valueOf(maxCount));

        bw.flush();
        bw.close();
        br.close();
    }

}
