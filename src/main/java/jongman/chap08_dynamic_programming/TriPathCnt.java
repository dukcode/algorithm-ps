package jongman.chap08_dynamic_programming;

import java.io.*;
import java.util.StringTokenizer;

public class TriPathCnt {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int c;
    private static int n;
    private static int[][] triangle;

    private static int[][] maxSum;
    private static int[][] cnt;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            input();

            maxSum = new int[n][n];
            findMaxSum(0, 0);

            cnt = new int[n][n];
            bw.write(String.valueOf(findCnt(0, 0)));
            bw.newLine();
        }


        br.close();
        bw.close();
    }

    private static int findCnt(int y, int x) {
        if (y == n - 1) {
            return cnt[y][x] = 1;
        }

        if (cnt[y][x] != 0) {
            return cnt[y][x];
        }

        cnt[y][x] = 0;

        if (findMaxSum(y + 1, x) >= findMaxSum(y + 1, x + 1)) {
            cnt[y][x] += findCnt(y + 1, x);
        }

        if (findMaxSum(y + 1, x) <= findMaxSum(y + 1, x + 1)) {
            cnt[y][x] += findCnt(y + 1, x + 1);
        }

        return cnt[y][x];
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        triangle = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x <= y; ++x) {
                triangle[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int findMaxSum(int y, int x) {
        if (y == n) {
            return 0;
        }

        if (maxSum[y][x] != 0) {
            return maxSum[y][x];
        }

        int left = findMaxSum(y + 1, x);
        int right = findMaxSum(y + 1, x + 1);

        return maxSum[y][x] = triangle[y][x] + Math.max(left, right);
    }

}
