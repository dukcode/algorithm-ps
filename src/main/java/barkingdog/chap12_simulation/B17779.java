package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17779 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] board;

    private static int ans = Integer.MAX_VALUE;

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

        for (int y = 0; y < n - 2; ++y) {
            for (int x = 1; x < n - 1; ++x) {
                for (int d1 = 1; d1 <= x; ++d1) {
                    for (int d2 = 1; d2 < n - x; ++d2) {
                        if (d1 + d2 + y >= n) {
                            continue;
                        }
                        ans = Math.min(ans, func(y, x, d1, d2));
                    }
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int func(int sy, int sx, int d1, int d2) {
        int[][] visited = new int[n][n];
        int[] population = new int[5];

        // 5구역
        for (int dy = 0; dy <= d1 + d2; ++dy) {
            int left = dy <= d1 ? dy * -1 : dy - d1 * 2;
            int right = dy <= d2 ? dy : d2 * 2 - dy;
            for (int dx = left; dx <= right; ++dx) {
                visited[sy + dy][sx + dx] = 5;
                population[4] += board[sy + dy][sx + dx];
            }
        }

        // 1 구역
        for (int y = 0; y < sy + d1; ++y) {
            for (int x = 0; x <= sx; ++x) {
                if (visited[y][x] != 0) {
                    continue;
                }
                visited[y][x] = 1;
                population[0] += board[y][x];
            }
        }

        // 2 구역
        for (int y = 0; y <= sy + d2; ++y) {
            for (int x = sx + 1; x < n; ++x) {
                if (visited[y][x] != 0) {
                    continue;
                }
                visited[y][x] = 2;
                population[1] += board[y][x];
            }
        }

        // 3 구역
        for (int y = sy + d1; y < n; ++y) {
            for (int x = 0; x < sx - d1 + d2; ++x) {
                if (visited[y][x] != 0) {
                    continue;
                }
                visited[y][x] = 3;
                population[2] += board[y][x];
            }
        }

        // 4 구역
        for (int y = sy + d2; y < n; ++y) {
            for (int x = sx - d1 + d2; x < n; ++x) {
                if (visited[y][x] != 0) {
                    continue;
                }
                visited[y][x] = 4;
                population[3] += board[y][x];
            }
        }

        Arrays.sort(population);

        return population[4] - population[0];
    }

    private static void print(int[][] visited) {
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                System.out.print(visited[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
