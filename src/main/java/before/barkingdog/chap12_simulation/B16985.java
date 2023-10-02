package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16985 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] dz = {1, 0, 0, -1, 0, 0};
    private static int[] dy = {0, 1, 0, 0, -1, 0};
    private static int[] dx = {0, 0, 1, 0, 0, -1};

    private static int[] orders = new int[5];    // 0 ~ 4
    private static boolean[] isUsed = new boolean[5];
    private static int[] rotates = new int[5];   // 0 ~ 3

    private static int[][][][] boards = new int[5][4][][];
    private static int[][][] resultBoard = new int[5][][];

    private static int[][][] dist;

    private static class Point {

        int z;
        int y;
        int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }

    }

    private static int ans = Integer.MAX_VALUE;

    private static void getRotates(int idx) {
        if (idx == 5) {
            for (int i = 0; i < 5; ++i) {
                resultBoard[i] = boards[orders[i]][rotates[i]];
            }
            dfs();
            return;
        }

        for (int i = 0; i < 4; ++i) {
            rotates[idx] = i;
            getRotates(idx + 1);
        }
    }

    private static void dfs() {

        if (resultBoard[0][0][0] == 0) {
            return;
        }

        dist = new int[5][5][5];
        dist[0][0][0] = 1;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 6; ++i) {
                int nz = cur.z + dz[i];
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (nz < 0 || nz >= 5 || ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
                    continue;
                }

                if (resultBoard[nz][ny][nx] == 0 || dist[nz][ny][nx] != 0) {
                    continue;
                }

                if (nz == 4 && ny == 4 && nx == 4) {
                    ans = Math.min(ans, dist[cur.z][cur.y][cur.x]);
                    return;
                }

                dist[nz][ny][nx] = dist[cur.z][cur.y][cur.x] + 1;
                q.offer(new Point(nz, ny, nx));
            }
        }
    }

    private static void getOrders(int idx) {
        if (idx == 5) {
            getRotates(0);
            return;
        }

        for (int i = 0; i < 5; ++i) {
            if (isUsed[i]) {
                continue;
            }

            orders[idx] = i;
            isUsed[i] = true;
            getOrders(idx + 1);
            isUsed[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int z = 0; z < 5; ++z) {
            int[][] board = new int[5][];
            for (int y = 0; y < 5; ++y) {
                st = new StringTokenizer(br.readLine());
                int[] line = new int[5];
                for (int x = 0; x < 5; ++x) {
                    line[x] = Integer.parseInt(st.nextToken());
                }
                board[y] = line;
            }
            boards[z][0] = board;
        }

        init();
        getOrders(0);

        if (ans == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void init() {
        for (int numBoard = 0; numBoard < 5; ++numBoard) {
            for (int count = 1; count <= 3; ++count) {
                rotate(numBoard, count);
            }
        }
    }

    private static void rotate(int numBoard, int count) {
        int[][] ret = new int[5][5];
        int from = count - 1;
        int[][] board = boards[numBoard][from];

        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                ret[4 - y][x] = board[y][x];
            }
        }

        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < y; ++x) {
                int tmp = ret[y][x];
                ret[y][x] = ret[x][y];
                ret[x][y] = tmp;
            }
        }

        boards[numBoard][count] = ret;
    }

}
