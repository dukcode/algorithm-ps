package before.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1780 {

    static int[][] paper;

    static class Ans {

        int numNegative1;
        int num0;
        int num1;

        public Ans() {
        }

        public Ans(int num) {
            if (num == -1) {
                numNegative1 = 1;
            } else if (num == 0) {
                num0 = 1;
            } else {
                num1 = 1;
            }
        }

        public Ans plus(Ans ans) {
            this.numNegative1 += ans.numNegative1;
            this.num0 += ans.num0;
            this.num1 += ans.num1;

            return this;
        }
    }

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

        Ans ans = func(0, 0, n);
        bw.write(String.valueOf(ans.numNegative1));
        bw.newLine();
        bw.write(String.valueOf(ans.num0));
        bw.newLine();
        bw.write(String.valueOf(ans.num1));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static Ans func(int r, int c, int size) {
        int num = paper[r][c];
        boolean isAllSame = true;
        Loop:
        for (int y = r; y < r + size; ++y) {
            for (int x = c; x < c + size; ++x) {
                if (paper[y][x] != num) {
                    isAllSame = false;
                    break Loop;
                }
            }
        }

        if (isAllSame) {
            return new Ans(paper[r][c]);
        }

        Ans ans = new Ans();
        for (int y = r; y < r + size; y = y + size / 3) {
            for (int x = c; x < c + size; x = x + size / 3) {
                ans.plus(func(y, x, size / 3));
            }
        }

        return ans;
    }

}
