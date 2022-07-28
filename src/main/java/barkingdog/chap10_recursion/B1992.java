package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1992 {

    static int[][] image;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        image = new int[n][n];

        for (int y = 0; y < n; ++y) {
            String line = br.readLine();
            for (int x = 0; x < n; ++x) {
                image[y][x] = line.charAt(x) - '0';
            }
        }

        func(0, 0, n);

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int r, int c, int n) {
        boolean same = true;
        int color = image[r][c];
        Loop:
        for (int y = r; y < r + n; ++y) {
            for (int x = c; x < c + n; ++x) {
                if (image[y][x] != color) {
                    same = false;
                    break Loop;
                }
            }
        }

        if (same) {
            sb.append(color);
            return;
        }

        int half = n / 2;
        sb.append("(");
        for (int i = 0; i < 4; ++i) {
            func(r + (i / 2) * half, c + (i % 2) * half, half);
        }
        sb.append(")");
    }

}
