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

public class B4991 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;

    private static int[][] board;


    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static List<Point> dusts;
    private static int[][][] distances;
    private static int[] order;
    private static boolean[] isUsed;

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {-1, 1, 0, 0};

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Loop:
        while (true) {
            ans = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (h == 0 && w == 0) {
                break;
            }

            board = new int[h][w];
            dusts = new ArrayList<>();
            for (int y = 0; y < h; ++y) {
                String line = br.readLine();
                for (int x = 0; x < w; ++x) {
                    char c = line.charAt(x);

                    if (c == 'o') {
                        dusts.add(0, new Point(y, x));
                    } else if (c == '*') {
                        dusts.add(new Point(y, x));
                        board[y][x] = 1;
                    } else if (c == 'x') {
                        board[y][x] = -1;
                    }
                }
            }
            distances = new int[dusts.size()][h][w];
            for (int i = 0; i < dusts.size(); ++i) {
                bfs(dusts.get(i), i);
            }

            for (int i = 0; i < dusts.size(); ++i) {
                Point dust = dusts.get(i);
                boolean impossible = true;
                for (int j = 0; j < dusts.size(); ++j) {
                    if (i == j) {
                        continue;
                    }

                    if (distances[j][dust.y][dust.x] != 0) {
                        impossible = false;
                        break;
                    }
                }

                if (impossible) {
                    bw.write(String.valueOf(-1));
                    bw.newLine();
                    continue Loop;
                }
            }

            order = new int[dusts.size()];
            isUsed = new boolean[dusts.size()];
            func(0, 0);

            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.close();
        br.close();

    }

    private static void func(int idx, int time) {
        if (idx == dusts.size()) {
            ans = Math.min(ans, time);
            return;
        }

        if (idx == 0) {
            isUsed[0] = true;
            order[0] = 0;
            func(1, 0);
            return;
        }

        for (int i = 1; i < dusts.size(); ++i) {
            if (isUsed[i]) {
                continue;
            }

            Point nextDust = dusts.get(i);
            order[idx] = i;

            isUsed[i] = true;
            func(idx + 1, time + distances[order[idx - 1]][nextDust.y][nextDust.x] - 1);

            isUsed[i] = false;
        }
    }

    private static void bfs(Point dust, int idx) {
        Queue<Point> q = new LinkedList<>();
        int[][] dist = distances[idx];

        q.offer(dust);
        dist[dust.y][dust.x] = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == -1 || dist[ny][nx] != 0) {
                    continue;
                }

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                q.offer(new Point(ny, nx));
            }
        }
    }
}
