package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int l;
    private static int r;
    private static int[][] board;
    private static boolean[][] visited;

    private static final int[] dy = {0, 0, 1, -1};
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

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (day++ <= 2000) {

            visited = new boolean[n][n];
            Queue<Point> q = new LinkedList<>();
            boolean isChanged = false;
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    if (visited[y][x]) {
                        continue;
                    }

                    List<Point> list = new ArrayList<>();
                    Point point = new Point(y, x);
                    q.offer(point);
                    list.add(point);
                    visited[y][x] = true;
                    int sum = board[y][x];

                    while (!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int i = 0; i < 4; ++i) {
                            int ny = cur.y + dy[i];
                            int nx = cur.x + dx[i];

                            if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                                continue;
                            }

                            if (visited[ny][nx]) {
                                continue;
                            }

                            int diff = Math.abs(board[cur.y][cur.x] - board[ny][nx]);
                            if (diff < l || r < diff) {
                                continue;
                            }

                            Point np = new Point(ny, nx);
                            q.offer(np);
                            visited[ny][nx] = true;
                            list.add(np);
                            sum += board[ny][nx];
                        }
                    }

                    int avgPop = sum / list.size();
                    for (Point p : list) {
                        if (board[p.y][p.x] != avgPop) {
                            isChanged = true;
                        }
                        board[p.y][p.x] = avgPop;
                    }
                }
            }

            if (!isChanged) {
                day--;
                break;
            }
        }

        bw.write(String.valueOf(day));

        bw.flush();
        bw.close();
        br.close();
    }

}
