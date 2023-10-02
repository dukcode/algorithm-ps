package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14503 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int y;
    private static int x;
    private static int dir;

    private static int[][] board;
    private static boolean[][] isCleaned;

    // north, east, south, west
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();

        Loop:
        while (true) {
            if (!isCleaned[y][x]) {
                isCleaned[y][x] = true;
                ans++;
            }
            for (int i = 0; i < 4; ++i) {

                dir = (dir + 3) % 4;

                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (board[ny][nx] == 1 || isCleaned[ny][nx]) {
                    continue;
                }

                y = ny;
                x = nx;
                continue Loop;
            }

            int backY = y + dy[(dir + 2) % 4];
            int backX = x + dx[(dir + 2) % 4];
            if (board[backY][backX] == 1) {
                break;
            } else {
                y = backY;
                x = backX;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        isCleaned = new boolean[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
