package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146 {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] board;
    static int[][] island;
    static int[][] dist;

    static int n;

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

        getInput(br);

        classifyIsland();
        int ans = findShortestDistance();

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int findShortestDistance() {
        int ans = Integer.MAX_VALUE;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (island[y][x] == 0) {
                    continue;
                }

                dist = new int[n][n];
                for (int i = 0; i < n; ++i) {
                    Arrays.fill(dist[i], -1);
                }

                Queue<Point> q = new LinkedList<>();

                int islandNum = island[y][x];
                dist[y][x] = 0;
                q.offer(new Point(y, x));

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (!inRange(ny, nx)) {
                            continue;
                        }

                        if (island[ny][nx] == islandNum) {
                            continue;
                        }

                        if (dist[ny][nx] != -1 && dist[ny][nx] <= dist[cur.y][cur.x] + 1) {
                            continue;
                        }

                        if (island[ny][nx] != 0) {
                            ans = Math.min(ans, dist[cur.y][cur.x]);
                            continue;
                        }

                        dist[ny][nx] = dist[cur.y][cur.x] + 1;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
        return ans;
    }

    private static void getInput(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        island = new int[n][n];

        StringTokenizer st = null;
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken()); // board 배열 초기화
            }
        }
    }

    private static void classifyIsland() {
        Queue<Point> q = new LinkedList<>();
        int count = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (board[y][x] == 0 || island[y][x] != 0) {
                    continue;
                }

                count++;
                island[y][x] = count;
                q.offer(new Point(y, x));

                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (!inRange(ny, nx)) {
                            continue;
                        }

                        if (board[ny][nx] == 0 || island[ny][nx] != 0) {
                            continue;
                        }

                        island[ny][nx] = count;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
    }

    static boolean inRange(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}
