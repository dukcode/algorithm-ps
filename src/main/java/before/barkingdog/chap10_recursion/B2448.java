package before.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2448 {

    static boolean[][] image;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        image = new boolean[n][2 * n - 1];

        func(0, n - 1, n);

        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < 2 * n - 1; ++x) {
                if (image[y][x]) {
                    bw.write('*');
                } else {
                    bw.write(' ');
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int r, int c, int n) {

        if (n == 3) {
            image[r][c] = true;
            image[r + 1][c - 1] = true;
            image[r + 1][c + 1] = true;
            for (int x = c - 2; x <= c + 2; ++x) {
                image[r + 2][x] = true;
            }
            return;
        }

        func(r, c, n / 2);
        func(r + n / 2, c - n / 2, n / 2);
        func(r + n / 2, c + n / 2, n / 2);
    }

}
