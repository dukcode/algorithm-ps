package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573_1 {

    static int[][] board;
    static boolean[][] visited1;
    static boolean[][] visited2;

    static int h;
    static int w;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

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

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        visited1 = new boolean[h][w];

        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        int year = 0;

        while (count == 1) {
            year++;
            melting();
            count = getCount();
        }

        if (count == 2) {
            bw.write(String.valueOf(year));
        } else {
            bw.write('0');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCount() {
        int count = 0;
        Queue<Point> q = new LinkedList<>();
        visited2 = new boolean[h][w];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (visited2[y][x] || board[y][x] == 0) {
                    continue;
                }

                visited2[y][x] = true;
                q.offer(new Point(y, x));
                count++;

                if (count == 2) {
                    return count;
                }

                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (outOfRange(ny, nx)) {
                            continue;
                        }

                        if (visited2[ny][nx] || board[ny][nx] == 0) {
                            continue;
                        }

                        visited2[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
        return count;
    }

    private static void melting() {
        visited1 = new boolean[h][w];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 0) {
                    continue;
                }

                visited1[y][x] = true;
                for (int i = 0; i < 4; ++i) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (outOfRange(ny, nx)) {
                        continue;
                    }

                    if (visited1[ny][nx]) {
                        continue;
                    }

                    if (board[ny][nx] == 0) {
                        board[y][x] = board[y][x] == 0 ? 0 : board[y][x] - 1;
                    }
                }
            }
        }
    }

    private static boolean outOfRange(int ny, int nx) {
        return ny < 0 || ny >= h || nx < 0 || nx >= w;
    }

}
