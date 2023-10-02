package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15683_1 {

    public static final int WALL = 6;

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int h;
    private static int w;

    private static int[][] boardOriginal;
    private static int[][] board;

    private static final List<Point> cctvPos = new ArrayList<>();
    private static final List<Point> cctvPos5 = new ArrayList<>();

    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int ans = Integer.MAX_VALUE;


    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        getInput();

        for (Point point : cctvPos5) {
            for (int dir = 0; dir < 4; ++dir) {
                record(boardOriginal, point, dir);
            }
        }

        // for (int y = 0; y < h; ++y) {
        //     for (int x = 0; x < w; ++x) {
        //         System.out.print(boardOriginal[y][x] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        for (int brute = 0; brute < (1 << (cctvPos.size() * 2)); ++brute) {
            initBoard();
            int order = brute;
            for (Point pos : cctvPos) {
                int cctv = boardOriginal[pos.y][pos.x];
                int dir = order % 4;
                order /= 4;

                switch (cctv) {
                    case 1:
                        record(board, pos, dir);
                        break;
                    case 2:
                        record(board, pos, dir);
                        record(board, pos, dir + 2);
                        break;
                    case 3:
                        record(board, pos, dir);
                        record(board, pos, dir + 1);
                        break;
                    case 4:
                        record(board, pos, dir);
                        record(board, pos, dir + 1);
                        record(board, pos, dir + 2);
                        break;
                    default:
                        break;
                }
            }
            // for (int y = 0; y < h; ++y) {
            //     for (int x = 0; x < w; ++x) {
            //         System.out.print(board[y][x] + " ");
            //     }
            //     System.out.println();
            // }
            // int cnt = cntBlindSpot();
            // System.out.println("cnt = " + cnt);
            // System.out.println();
            ans = Math.min(ans, cntBlindSpot());
        }

        bw.write(String.valueOf(ans));

        bw.close();
        br.close();
    }

    private static void record(int[][] targetBoard, Point pos, int dir) {
        dir %= 4;
        int y = pos.y;
        int x = pos.x;
        int cctv = targetBoard[y][x];
        while (true) {
            y += dy[dir];
            x += dx[dir];

            if (y < 0 || y >= h || x < 0 || x >= w) {
                break;
            }

            if (targetBoard[y][x] == WALL) {
                break;
            }

            if (targetBoard[y][x] == 0) {
                targetBoard[y][x] = cctv;
            }
        }
    }

    private static void initBoard() {
        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            System.arraycopy(boardOriginal[y], 0, board[y], 0, w);
        }
    }

    private static int cntBlindSpot() {
        int ret = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == 0) {
                    ret++;
                }
            }
        }

        return ret;
    }

    private static void getInput() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        boardOriginal = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                int cctv = Integer.parseInt(st.nextToken());
                if (1 <= cctv && cctv <= 4) {
                    cctvPos.add(new Point(y, x));
                }
                if (cctv == 5) {
                    cctvPos5.add(new Point(y, x));
                }
                boardOriginal[y][x] = cctv;
            }
        }
    }

}

