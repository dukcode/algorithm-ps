package before.review.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9663 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static boolean[] vertical;
    private static boolean[] rightDown;
    private static boolean[] leftDown;

    private static long ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        vertical = new boolean[n];
        rightDown = new boolean[2 * n - 1];
        leftDown = new boolean[2 * n - 1];

        ans = func(0);
        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static long func(int y) {
        if (y == n) {
            return 1L;
        }

        long ret = 0;
        for (int x = 0; x < n; ++x) {
            if (vertical[x] || rightDown[x - y + (n - 1)] || leftDown[x + y]) {
                continue;
            }

            vertical[x] = true;
            rightDown[x - y + (n - 1)] = true;
            leftDown[x + y] = true;
            ret += func(y + 1);
            vertical[x] = false;
            rightDown[x - y + (n - 1)] = false;
            leftDown[x + y] = false;
        }

        return ret;
    }

}
