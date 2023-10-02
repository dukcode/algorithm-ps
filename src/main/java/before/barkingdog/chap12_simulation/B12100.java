package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B12100 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] board;
    private static int[] line;

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        func(0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void printBoard() {
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                System.out.printf("%2d ", board[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void func(int cnt) {
        if (cnt == 5) {
            ans = Math.max(ans, getMaxValue());
            return;
        }

        int[][] temp = new int[n][n];
        copyArray(board, temp);
        for (int dir = 0; dir < 4; ++dir) {
            rotate(dir);
            moveLeft();
            func(cnt + 1);
            copyArray(temp, board);
        }
    }

    private static void rotate(int dir) {

        while (dir-- > 0) {
            for (int y = 0; y < n; ++y) {
                for (int x = y; x < n; ++x) {
                    int tmp = board[y][x];
                    board[y][x] = board[x][y];
                    board[x][y] = tmp;
                }
            }

            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n / 2; ++x) {
                    int tmp = board[y][x];
                    board[y][x] = board[y][(n - 1) - x];
                    board[y][(n - 1) - x] = tmp;
                }
            }
        }
    }

    private static void copyArray(int[][] src, int[][] dest) {
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                dest[y][x] = src[y][x];
            }
        }
    }

    private static void moveLeft() {
        for (int y = 0; y < n; ++y) {
            line = new int[n];
            int lastJoinedIdx = -1;
            int lastNumIdx = -1;
            for (int x = 0; x < n; ++x) {
                if (board[y][x] == 0) {
                    continue;
                }

                if (lastNumIdx == -1) {
                    lastNumIdx++;
                    line[lastNumIdx] = board[y][x];
                    continue;
                }

                if (lastJoinedIdx != lastNumIdx && line[lastNumIdx] == board[y][x]) {
                    lastJoinedIdx = lastNumIdx;
                    line[lastNumIdx] += board[y][x];
                } else {
                    lastNumIdx++;
                    line[lastNumIdx] = board[y][x];
                }

            }
            board[y] = line;
        }
    }

    private static int getMaxValue() {
        int ret = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                ret = Math.max(ret, board[y][x]);
            }
        }

        return ret;
    }

}
