package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class B10026 {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static char[][] board;
    static boolean[][] visited1;    // 적록생약 X : R, G, B 따로 보임
    static boolean[][] visited2;    // 적록색약 O : RG, B 따로보임

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

        int n = Integer.parseInt(br.readLine());
        board = new char[n][];
        visited1 = new boolean[n][n];
        visited2 = new boolean[n][n];

        for (int i = 0; i < n; ++i) {
            board[i] = br.readLine().toCharArray();
        }

        int ans1 = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (visited1[y][x]) {
                    continue;
                }

                ans1++;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(y, x));
                visited1[y][x] = true;

                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        if (visited1[ny][nx] || board[cur.y][cur.x] != board[ny][nx]) {
                            continue;
                        }

                        visited1[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }

        int ans2 = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (visited2[y][x]) {
                    continue;
                }

                ans2++;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(y, x));
                visited2[y][x] = true;

                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        if (visited2[ny][nx]) {
                            continue;
                        }

                        char color = board[cur.y][cur.x];
                        if (color == 'B') {
                            if (board[ny][nx] != 'B') {
                                continue;
                            }
                        } else {
                            if (board[ny][nx] == 'B') {
                                continue;
                            }
                        }

                        visited2[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
        bw.write(String.valueOf(ans1));
        bw.write(' ');
        bw.write(String.valueOf(ans2));

        bw.flush();
        bw.close();
        br.close();
    }

}
