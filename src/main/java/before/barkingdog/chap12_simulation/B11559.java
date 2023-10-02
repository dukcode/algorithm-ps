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

public class B11559 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static char[][] board = new char[12][];

    private static final int H = 12;
    private static final int W = 6;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};

    private static boolean[][] visited;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < H; ++i) {
            board[i] = br.readLine().toCharArray();
        }

        while (pop()) {
            gravity();
            ans++;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean pop() {
        boolean isPopped = false;
        visited = new boolean[H][W];

        for (int y = 0; y < H; ++y) {
            for (int x = 0; x < W; ++x) {
                if (board[y][x] == '.' || visited[y][x]) {
                    continue;
                }

                Queue<Point> q = new LinkedList<>();
                List<Point> popList = new ArrayList<>();

                q.offer(new Point(y, x));
                popList.add(new Point(y, x));
                visited[y][x] = true;

                char color = board[y][x];

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                            continue;
                        }

                        if (visited[ny][nx]) {
                            continue;
                        }

                        if (board[ny][nx] != color) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        popList.add(new Point(ny, nx));
                        q.offer(new Point(ny, nx));
                    }
                }

                if (popList.size() >= 4) {
                    isPopped = true;
                    for (Point p : popList) {
                        board[p.y][p.x] = '.';
                    }
                }
            }
        }

        return isPopped;
    }

    private static void gravity() {
        for (int x = 0; x < W; ++x) {
            int pos = H - 1;
            for (int y = H - 1; y >= 0; --y) {
                if (board[y][x] == '.') {
                    continue;
                }

                board[pos][x] = board[y][x];
                pos--;
            }

            while (pos >= 0) {
                board[pos][x] = '.';
                pos--;
            }
        }
    }

}
