package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class B1941 {

    public static final int N = 5;
    private static final char[][] board = new char[N][N];

    private static int ans = 0;
    private static final boolean[][] selected = new boolean[N][N];
    private static final int[] arr = new int[7];

    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; ++i) {
            board[i] = br.readLine().toCharArray();
        }

        func(0, 0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int minIdx, int cnt, int cntS) {
        if (cnt == 7) {
            if (cntS >= 4 && isAdjacent()) {
                ans++;
                return;
            }
            return;
        }

        for (int i = minIdx; i < N * N; ++i) {
            int y = i / 5;
            int x = i % 5;

            arr[cnt] = i;
            selected[y][x] = true;
            if (board[y][x] == 'S') {
                func(i + 1, cnt + 1, cntS + 1);
            } else {
                func(i + 1, cnt + 1, cntS);
            }
            selected[y][x] = false;
        }
    }

    private static boolean isAdjacent() {
        boolean[][] visited = new boolean[N][N];
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;

        q.offer(arr[0]);
        int firstY = arr[0] / 5;
        int firstX = arr[0] % 5;
        visited[firstY][firstX] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / N;
            int x = cur % N;

            cnt++;

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                    continue;
                }

                if (!selected[ny][nx] || visited[ny][nx]) {
                    continue;
                }

                q.offer(ny * N + nx);
                visited[ny][nx] = true;
            }
        }

        return cnt == 7;
    }
}
