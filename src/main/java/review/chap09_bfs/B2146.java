package review.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] board;
    private static int[][] island;
    private static int[][] dist;

    private static int ans = Integer.MAX_VALUE;

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {1, -1, 0, 0};

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        island = new int[n][n];
        dist = new int[n][n];

        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        classifyIsland();

        Queue<Point> q = new LinkedList<>();
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (island[y][x] != 0) {
                    q.offer(new Point(y, x));
                }
            }
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int numIsland = island[cur.y][cur.x];
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (outOfRange(ny, nx) || island[ny][nx] == numIsland) {
                    continue;
                }

                if (island[ny][nx] != 0) {
                    ans = Math.min(ans, dist[ny][nx] + dist[cur.y][cur.x]);
                    continue;
                }

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                island[ny][nx] = island[cur.y][cur.x];
                q.offer(new Point(ny, nx));

            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void printIsland() {
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                System.out.printf("%2d ", island[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void classifyIsland() {
        Queue<Point> q = new LinkedList<>();

        int numIsland = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (board[y][x] == 0 || island[y][x] != 0) {
                    continue;
                }

                numIsland++;
                island[y][x] = numIsland;
                q.offer(new Point(y, x));

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (outOfRange(ny, nx)) {
                            continue;
                        }

                        if (board[ny][nx] == 0 || island[ny][nx] != 0) {
                            continue;
                        }

                        island[ny][nx] = numIsland;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
    }

    private static boolean outOfRange(int ny, int nx) {
        return ny < 0 || ny >= n || nx < 0 || nx >= n;
    }

}
