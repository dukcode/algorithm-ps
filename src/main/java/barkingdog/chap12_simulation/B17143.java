package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B17143 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int m;

    private static int r;
    private static int c;
    private static int s;
    private static int d; // 0 위, 1 아래, 2 오, 3 왼
    private static int z;

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, 1, -1};

    private static Shark[][] board;

    private static class Shark {

        int dir;
        int vel;
        int size;

        public Shark(int dir, int vel, int size) {
            this.dir = dir;
            this.vel = vel;
            this.size = size;
        }
    }

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new Shark[h][w];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()) - 1;
            z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(d, s, z);
            board[r][c] = shark;
        }

        for (int i = 0; i < w; ++i) {
            catchShark(i);
            moveShark();
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void moveShark() {
        Shark[][] newBoard = new Shark[h][w];
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] == null) {
                    continue;
                }

                Shark shark = board[y][x];

                int ny = y;
                int nx = x;
                int nDir = shark.dir;

                if (shark.dir / 2 == 0) {
                    for (int i = 0; i < shark.vel; ++i) {
                        ny += dy[nDir];

                        if (ny < 0 || ny >= h) {
                            ny -= dy[nDir] * 2;
                            nDir = nDir % 2 == 1 ? nDir - 1 : nDir + 1;
                        }
                    }
                } else {
                    for (int i = 0; i < shark.vel; ++i) {
                        nx += dx[nDir];

                        if (nx < 0 || nx >= w) {
                            nx -= dx[nDir] * 2;
                            nDir = nDir % 2 == 1 ? nDir - 1 : nDir + 1;
                        }
                    }
                }

                shark.dir = nDir;
                if (newBoard[ny][nx] == null ||
                        newBoard[ny][nx].size < shark.size) {
                    newBoard[ny][nx] = shark;
                }
            }
        }

        board = newBoard;
    }

    private static void catchShark(int col) {
        for (int row = 0; row < h; ++row) {
            if (board[row][col] != null) {
                ans += board[row][col].size;
                board[row][col] = null;
                break;
            }
        }
    }

}
