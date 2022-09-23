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

public class B14502 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    public static int[][] board;

    public static int[] dy = {0, 0, 1, -1};
    public static int[] dx = {1, -1, 0, 0};

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static List<Point> grounds = new ArrayList<>();
    public static Queue<Point> viruses = new LinkedList<>();
    public static int[] wallIdx = new int[3];

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                int block = Integer.parseInt(st.nextToken());

                if (block == 0) {
                    grounds.add(new Point(y, x));
                } else if (block == 2) {
                    viruses.offer(new Point(y, x));
                }
                board[y][x] = block;
            }
        }

        func(0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx) {
        if (idx == 3) {
            bfs();
            return;
        }

        for (int i = startIdx; i < grounds.size(); ++i) {
            wallIdx[idx] = i;
            func(idx + 1, i + 1);
        }
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>(viruses);

        int[][] tmpBoard = new int[h][w];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                tmpBoard[y][x] = board[y][x];
            }
        }

        for (int i = 0; i < 3; ++i) {
            Point ground = grounds.get(wallIdx[i]);
            tmpBoard[ground.y][ground.x] = 3;
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (tmpBoard[ny][nx] != 0) {
                    continue;
                }

                tmpBoard[ny][nx] = 2;
                q.offer(new Point(ny, nx));
            }
        }

        int area = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                area += tmpBoard[y][x] == 0 ? 1 : 0;
            }
        }

        ans = Math.max(ans, area);
    }

}
