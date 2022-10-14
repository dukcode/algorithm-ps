package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B4883 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] arr;
    private static int[][] cache;

    private static final int[] dy = {-1, -1, -1, 0};
    private static final int[] dx = {-1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = 0;
        while (true) {
            cnt++;
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            arr = new int[n][3];
            for (int y = 0; y < n; ++y) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < 3; ++x) {
                    arr[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            cache = new int[n][3];
            cache[0][0] = Integer.MAX_VALUE;
            cache[0][1] = arr[0][1];
            cache[0][2] = arr[0][1] + arr[0][2];
            for (int y = 1; y < n; ++y) {
                for (int x = 0; x < 3; ++x) {
                    int cost = Integer.MAX_VALUE;
                    for (int i = 0; i < 4; ++i) {
                        int ny = y + dy[i];
                        int nx = x + dx[i];

                        if (nx < 0 || nx >= 3) {
                            continue;
                        }

                        cost = Math.min(cost, cache[ny][nx]);
                    }

                    cache[y][x] = arr[y][x] + cost;
                }
            }

            bw.write(String.valueOf(cnt));
            bw.write(". ");
            bw.write(String.valueOf(cache[n - 1][1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
