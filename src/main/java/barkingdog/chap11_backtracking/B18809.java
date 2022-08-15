package barkingdog.chap11_backtracking;

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

public class B18809 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int h;
    private static int w;
    private static int numGreen;
    private static int numRed;

    private static int[][] board;
    private static int[][] dist;
    private static List<Point> candidates = new ArrayList<>();
    private static int numCandidates;

    private static int[] permutation;

    private static final int GREEN = 1;
    private static final int RED = -1;
    private static final int NOTHING = 0;

    private static int ans = 0;

    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        func(0, 0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int countFlowers() {
        int ret = 0;
        dist = new int[h][w];
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < numCandidates; ++i) {
            if (permutation[i] != 0) {
                Point p = candidates.get(i);
                int y = p.y;
                int x = p.x;
                dist[y][x] = permutation[i];
                q.offer(candidates.get(i));
            }
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int now = dist[cur.y][cur.x] + (dist[cur.y][cur.x] > 0 ? 1 : -1);

            for (int i = 0; i < 4; ++i) {
                if (dist[cur.y][cur.x] == Integer.MAX_VALUE) {
                    continue;
                }

                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == 0) {
                    continue;
                }

                if (dist[ny][nx] != 0) {
                    if (dist[ny][nx] + now == 0) {
                        ret++;
                        dist[ny][nx] = Integer.MAX_VALUE;
                    }
                    continue;
                }

                dist[ny][nx] = now;
                q.offer(new Point(ny, nx));
            }
        }

        return ret;
    }

    private static void func(int idx, int cntGreen, int cntRed) {
        if (idx == numCandidates) {
            ans = Math.max(ans, countFlowers());
            return;
        }

        if (cntGreen < numGreen) {
            permutation[idx] = GREEN;
            func(idx + 1, cntGreen + 1, cntRed);
        }

        if (cntRed < numRed) {
            permutation[idx] = RED;
            func(idx + 1, cntGreen, cntRed + 1);
        }

        if (idx - cntGreen - cntRed < numCandidates - numGreen - numRed) {
            permutation[idx] = NOTHING;
            func(idx + 1, cntGreen, cntRed);
        }
    }


    private static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        numGreen = Integer.parseInt(st.nextToken());
        numRed = Integer.parseInt(st.nextToken());

        board = new int[h][w];

        // 0 = 호수, 1 = 배양액 X, 2 = 배양액 O
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                int ground = Integer.parseInt(st.nextToken());
                if (ground == 2) {
                    candidates.add(new Point(y, x));
                }
                board[y][x] = ground;
            }
        }

        numCandidates = candidates.size();
        permutation = new int[numCandidates];
    }

}
