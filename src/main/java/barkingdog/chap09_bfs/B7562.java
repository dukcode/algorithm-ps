package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562 {

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] board;

    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int l = Integer.parseInt(br.readLine());
            board = new int[l][l];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int nowX = Integer.parseInt(st1.nextToken());
            int nowY = Integer.parseInt(st1.nextToken());
            board[nowY][nowX] = 1;

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int lastX = Integer.parseInt(st2.nextToken());
            int lastY = Integer.parseInt(st2.nextToken());

            if (nowY == lastY && nowX == lastX) {
                bw.write("0");
                bw.newLine();
                continue;
            }

            int ans = 0;

            Queue<Point> q = new LinkedList<>();
            q.offer(new Point(nowY, nowX));

            Loop:
            while (!q.isEmpty()) {
                Point cur = q.poll();

                for (int i = 0; i < 8; ++i) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (ny == lastY && nx == lastX) {
                        ans = board[cur.y][cur.x];
                        break Loop;
                    }

                    if (ny < 0 || ny >= l || nx < 0 || nx >= l) {
                        continue;
                    }

                    if (board[ny][nx] != 0) {
                        continue;
                    }

                    board[ny][nx] = board[cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx));
                }
            }
            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
