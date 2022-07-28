package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2630 {

    static int[][] paper;

    static int[] count = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        paper = new int[n][n];

        for (int y = 0; y < n; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                paper[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        func(0, 0, n);

        bw.write(String.valueOf(count[0]));
        bw.newLine();
        bw.write(String.valueOf(count[1]));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int r, int c, int n) {
        int color = paper[r][c];
        boolean same = true;
        Loop:
        for (int y = r; y < r + n; ++y) {
            for (int x = c; x < c + n; ++x) {
                if (color != paper[y][x]) {
                    same = false;
                    break Loop;
                }
            }
        }

        if (same) {
            count[color]++;
            return;
        }

        for (int y = r; y < r + n; y = y + n / 2) {
            for (int x = c; x < c + n; x = x + n / 2) {
                func(y, x, n / 2);
            }
        }
    }

}
