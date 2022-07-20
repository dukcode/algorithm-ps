package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179 {

    static int h;
    static int w;

    // 벽 : -2
    // 불 : 0
    // 공간 : -1
    static int[][] board1;
    static int[][] board2;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static Point p;

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

        h = Integer.parseInt(st1.nextToken());
        w = Integer.parseInt(st1.nextToken());

        board1 = new int[h][w];
        board2 = new int[h][w];

        Queue<Point> q1 = new LinkedList<>();
        Queue<Point> q2 = new LinkedList<>();
        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                char block = line.charAt(x);
                switch (block) {
                    case '#':
                        board1[y][x] = -2;
                        board2[y][x] = -2;
                        break;
                    case 'F':
                        board1[y][x] = 0;
                        board2[y][x] = -1;
                        q1.offer(new Point(y, x));
                        break;
                    case '.':
                        board1[y][x] = -1;
                        board2[y][x] = -1;
                        break;
                    case 'J':
                        board1[y][x] = -1;
                        board2[y][x] = 0;
                        q2.offer(new Point(y, x));
                        break;
                    default:
                        break;
                }
            }
        }

        while (!q1.isEmpty()) {
            Point cur = q1.poll();
            int time = board1[cur.y][cur.x];

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board1[ny][nx] != -1) {
                    continue;
                }

                board1[ny][nx] = time + 1;
                q1.offer(new Point(ny, nx));
            }
        }

        int ans = 0;
        Loop:
        while (!q2.isEmpty()) {
            Point cur = q2.poll();
            int time = board2[cur.y][cur.x];

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    ans = time + 1;
                    break Loop;
                }

                if (board2[ny][nx] != -1) {
                    continue;
                }

                if (board1[ny][nx] != -1 && time + 1 >= board1[ny][nx]) {
                    continue;
                }

                board2[ny][nx] = time + 1;
                q2.offer(new Point(ny, nx));
            }
        }

        if (ans != 0) {
            bw.write(String.valueOf(ans));
        } else {
            bw.write("IMPOSSIBLE");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
