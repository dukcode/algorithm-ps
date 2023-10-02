package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B17144 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int t;

    private static int[][] board;

    private static int ans;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static final int CW = -1;
    private static final int CCW = 1;

    private static List<Point> airCleaners = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
                if (board[y][x] == -1) {
                    airCleaners.add(new Point(y, x));
                }
            }
        }

        while (t-- > 0) {
            diffuse();
            cycle(airCleaners.get(0), CCW);
            cycle(airCleaners.get(1), CW);
        }

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] != -1) {
                    ans += board[y][x];
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void cycle(Point start, int rotateDir) {
        if (start.y == 0 || start.y == h - 1) {
            return;
        }

        int dir = rotateDir == CCW ? 0 : 2; // 위, 아래
        Point p = new Point(start.y, start.x);

        while (true) {
            int ny = p.y + dy[dir];
            int nx = p.x + dx[dir];

            if (rotateDir == CCW) {
                if (ny < 0 || ny > start.y || nx < 0 || nx >= w) {
                    dir = (dir + rotateDir + 4) % 4;
                    continue;
                }
            } else {
                if (ny < start.y || ny >= h || nx < 0 || nx >= w) {
                    dir = (dir + rotateDir + 4) % 4;
                    continue;
                }
            }

            // end
            if (board[ny][nx] == -1) {
                board[p.y][p.x] = 0;
                break;
            }

            // start
            if (board[p.y][p.x] != -1) {
                board[p.y][p.x] = board[ny][nx];
            }

            p.y = ny;
            p.x = nx;
        }
    }


    private static void diffuse() {
        int[][] newBoard = new int[h][w];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] <= 0) {
                    newBoard[y][x] += board[y][x];
                    continue;
                }

                List<Point> points = new ArrayList<>();
                for (int i = 0; i < 4; ++i) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                        continue;
                    }

                    if (board[ny][nx] == -1) {
                        continue;
                    }

                    points.add(new Point(ny, nx));
                }

                int amountDiffused = board[y][x] / 5;
                int amountRemain = board[y][x] - amountDiffused * points.size();
                newBoard[y][x] += amountRemain;
                for (Point p : points) {
                    newBoard[p.y][p.x] += amountDiffused;
                }
            }
        }

        board = newBoard;
    }
}
