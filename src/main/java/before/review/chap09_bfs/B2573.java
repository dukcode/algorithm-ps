package before.review.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int[][] board;

    private static boolean[][] visited;

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {-1, 1, 0, 0};

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
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        board = new int[h][w];

        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {

            melting();
            year++;
            int count = getCount();

            if (count >= 2) {
                break;
            } else if (count == 0) {
                year = 0;
                break;
            }

        }

        bw.write(String.valueOf(year));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCount() {
        int ret = 0;
        visited = new boolean[h][w];

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 0 || visited[y][x]) {
                    continue;
                }

                ret++;
                visited[y][x] = true;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(y, x));

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (outOfRange(ny, nx)) {
                            continue;
                        }

                        if (board[ny][nx] == 0 || visited[ny][nx]) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }

        return ret;
    }

    private static void printBoard() {
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                System.out.printf("%2d ", board[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void melting() {

        visited = new boolean[h][w];

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 0) {
                    continue;
                }

                visited[y][x] = true;

                int countWater = 0;
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (outOfRange(ny, nx)) {
                        continue;
                    }

                    if (board[ny][nx] != 0 || visited[ny][nx]) {
                        continue;
                    }

                    countWater++;
                }

                board[y][x] = Math.max(board[y][x] - countWater, 0);
            }
        }
    }

    private static boolean outOfRange(int ny, int nx) {
        return ny < 0 || ny >= h || nx < 0 || nx >= w;
    }

}
