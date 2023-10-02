package before.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2447 {

    static boolean[][] img;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        img = new boolean[n][n];

        func(0, 0, n);

        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (img[y][x]) {
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
        if (n == 1) {
            img[r][c] = true;
            return;
        }

        int trisection = n / 3;
        for (int i = 0; i < 9; ++i) {
            if (i == 4) {
                continue;
            }
            func(r + (i / 3) * trisection, c + (i % 3) * trisection, trisection);
        }
    }

}
