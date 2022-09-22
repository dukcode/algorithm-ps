package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14499 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int y;
    private static int x;
    private static int k;

    private static int[][] board;
    private static int[] dirs;

    private static int[] dy = {0, 0, 0, -1, 1};
    private static int[] dx = {0, 1, -1, 0, 0};

    // back, top, front, bottom, left, right
    private static int[] dice = new int[6];
    private static final int BACK = 0;
    private static final int TOP = 1;
    private static final int FRONT = 2;
    private static final int BOTTOM = 3;
    private static final int LEFT = 4;
    private static final int RIGHT = 5;

    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;
    private static final int SOUTH = 4;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dirs = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; ++i) {
            dirs[i] = Integer.parseInt(st.nextToken());
        }

        for (int dir : dirs) {
            Integer top = moveDice(dir);

            if (top != null) {
                bw.write(String.valueOf(top));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static Integer moveDice(int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
            return null;
        }

        y = ny;
        x = nx;

        switch (dir) {
            case EAST:
                int tmp1 = dice[RIGHT];
                dice[RIGHT] = dice[TOP];
                dice[TOP] = dice[LEFT];
                dice[LEFT] = dice[BOTTOM];
                dice[BOTTOM] = tmp1;
                break;
            case WEST:
                int tmp2 = dice[LEFT];
                dice[LEFT] = dice[TOP];
                dice[TOP] = dice[RIGHT];
                dice[RIGHT] = dice[BOTTOM];
                dice[BOTTOM] = tmp2;
                break;
            case NORTH:
                int tmp3 = dice[BACK];
                dice[BACK] = dice[TOP];
                dice[TOP] = dice[FRONT];
                dice[FRONT] = dice[BOTTOM];
                dice[BOTTOM] = tmp3;
                break;
            case SOUTH:
                int tmp4 = dice[FRONT];
                dice[FRONT] = dice[TOP];
                dice[TOP] = dice[BACK];
                dice[BACK] = dice[BOTTOM];
                dice[BOTTOM] = tmp4;
                break;
            default:
                break;
        }
        if (board[ny][nx] == 0) {
            board[ny][nx] = dice[BOTTOM];
        } else {
            dice[BOTTOM] = board[ny][nx];
            board[ny][nx] = 0;
        }

        return dice[TOP];
    }

}
