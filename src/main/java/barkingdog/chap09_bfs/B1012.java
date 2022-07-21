package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1012 {

    static int[][] board;
    static boolean[][] visited;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

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

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            board = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < k; ++i) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                board[y][x] = 1;
            }

            int ans = 0;
            Queue<Point> q = new LinkedList<>();
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    if (board[y][x] == 0 || visited[y][x]) {
                        continue;
                    }

                    visited[y][x] = true;
                    q.offer(new Point(y, x));
                    ans++;

                    while (!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int i = 0; i < 4; ++i) {
                            int ny = cur.y + dy[i];
                            int nx = cur.x + dx[i];

                            if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                                continue;
                            }

                            if (visited[ny][nx] || board[ny][nx] == 0) {
                                continue;
                            }

                            visited[ny][nx] = true;
                            q.offer(new Point(ny, nx));
                        }
                    }
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
