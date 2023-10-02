package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B13460 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static char[][] board;

    private static int[] dirs = new int[10];


    // 오, 왼, 아래, 위
    private static final int[] dy = {0, 0, 1, -1};
    private static final int[] dx = {1, -1, 0, 0};

    private static Point red;
    private static Point blue;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public boolean equals(Point p) {
            return this.y == p.y && this.x == p.x;
        }
    }

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        board = new char[h][w];
        for (int y = 0; y < h; ++y) {
            board[y] = br.readLine().toCharArray();
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 'R') {
                    red = new Point(y, x);
                    board[y][x] = '.';
                } else if (board[y][x] == 'B') {
                    blue = new Point(y, x);
                    board[y][x] = '.';
                }
            }

        }

        func(0, red.y, red.x, blue.y, blue.x);

        if (ans == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int ry, int rx, int by, int bx) {
        if (idx == 10) {
            return;
        }

        if (ans <= idx + 1) {
            return;
        }

        for (int dir = 0; dir < 4; ++dir) {
            if (idx != 0 && dirs[idx - 1] == dir) {
                continue;
            }

            dirs[idx] = dir;

            int nby = by;
            int nbx = bx;
            int nry = ry;
            int nrx = rx;

            while (board[nby + dy[dir]][nbx + dx[dir]] == '.') {
                nby += dy[dir];
                nbx += dx[dir];
            }

            if (board[nby + dy[dir]][nbx + dx[dir]] == 'O') {
                continue;
            }

            while (board[nry + dy[dir]][nrx + dx[dir]] == '.') {
                nry += dy[dir];
                nrx += dx[dir];
            }

            if (board[nry + dy[dir]][nrx + dx[dir]] == 'O') {
                ans = Math.min(ans, idx + 1);
                break;
            }

            if (nry == nby && nrx == nbx) {
                // 오, 왼, 아래, 위
                switch (dir) {
                    case 0:
                        if (rx < bx) {
                            nrx--;
                        } else {
                            nbx--;
                        }
                        break;
                    case 1:
                        if (rx < bx) {
                            nbx++;
                        } else {
                            nrx++;
                        }
                        break;
                    case 2:
                        if (ry < by) {
                            nry--;
                        } else {
                            nby--;
                        }
                        break;
                    case 3:
                        if (ry < by) {
                            nby++;
                        } else {
                            nry++;
                        }
                        break;
                    default:
                        break;
                }
            }

            func(idx + 1, nry, nrx, nby, nbx);

        }
    }

    private static void printBoard(int ry, int rx, int by, int bx) {
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (y == ry && x == rx) {
                    System.out.print('R');
                } else if (y == by && x == bx) {
                    System.out.print('B');
                } else {
                    System.out.print(board[y][x]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
