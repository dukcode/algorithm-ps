package before.review.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class B1941 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static char[][] board = new char[5][];
    private static boolean[] taken = new boolean[25];
    private static int[] arr = new int[7];
    private static boolean[] visited;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        func(0, 0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx, int numS) {
        if (idx == 7) {
            if (numS < 4) {
                return;
            }

            if (is7Princess()) {
                ans++;
            }
            return;
        }

        for (int i = startIdx; i < 25; ++i) {
            arr[idx] = i;
            taken[i] = true;

            func(idx + 1, i + 1, board[i / 5][i % 5] == 'S' ? numS + 1 : numS);
            taken[i] = false;
        }
    }

    private static boolean is7Princess() {
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[0]);
        visited = new boolean[25];
        visited[arr[0]] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int ny = cur / 5 + dy[i];
                int nx = cur % 5 + dx[i];

                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
                    continue;
                }

                if (!taken[ny * 5 + nx]) {
                    continue;
                }

                if (visited[ny * 5 + nx]) {
                    continue;
                }

                visited[ny * 5 + nx] = true;
                q.offer(ny * 5 + nx);
            }
        }

        return count == 7;
    }

}
