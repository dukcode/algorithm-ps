package before.barkingdog.chap12_simulation;

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

public class B17141 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static int[][] board;

    private static List<Point> cands = new ArrayList<>();
    private static Point[] vPos;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {-1, 1, 0, 0};

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 2) {
                    cands.add(new Point(y, x));
                    continue;
                }

                board[y][x] = num * -1;
            }
        }

        vPos = new Point[m];
        func(0, 0);

        bw.write(String.valueOf(ans == Integer.MAX_VALUE ? -1 : ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx) {
        if (idx == m) {
            int time = bfs();
            if (time != -1) {
                ans = Math.min(ans, time);
            }
            return;
        }

        for (int i = startIdx; i < cands.size(); ++i) {
            vPos[idx] = cands.get(i);
            func(idx + 1, i + 1);
        }
    }

    private static int bfs() {
        int[][] dist = new int[n][n];
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            q.offer(vPos[i]);
            dist[vPos[i].y][vPos[i].x] = 1;
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    continue;
                }

                if (board[ny][nx] == -1 || dist[ny][nx] != 0) {
                    continue;
                }

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                q.offer(new Point(ny, nx));
            }
        }

        int cnt = 0;
        int ret = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (board[y][x] == 0) {
                    if (dist[y][x] == 0) {
                        cnt++;
                    } else {
                        ret = Integer.max(ret, dist[y][x]);
                    }
                }
            }
        }

        return cnt == 0 ? ret - 1 : -1;
    }

}
