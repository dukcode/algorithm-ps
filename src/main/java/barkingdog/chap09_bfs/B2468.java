package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2468 {

    static int n;

    static int[][] board;
    static boolean[][] visited;

    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, 1, -1, 0};

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

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;
        for (int y = 0; y < n; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                minHeight = Math.min(minHeight, height);
                board[y][x] = height;
            }
        }

        int ans = 1;

        for (int h = minHeight; h < maxHeight; ++h) {
            ans = Math.max(ans, bfs(h));
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int h) {
        visited = new boolean[n][n];
        Queue<Point> q = new LinkedList<>();

        int count = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (visited[y][x] || board[y][x] <= h) {
                    continue;
                }

                visited[y][x] = true;
                q.offer(new Point(y, x));
                count++;

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        if (visited[ny][nx] || board[ny][nx] <= h) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
        return count;
    }

}
