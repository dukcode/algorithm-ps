package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15684 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int h;

    private static boolean[][] ladder;

    private static List<Pos> candidates = new ArrayList<>();

    private static class Pos {

        int line;
        int row;

        public Pos(int line, int row) {
            this.line = line;
            this.row = row;
        }

    }

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[n][h + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int line = Integer.parseInt(st.nextToken());

            ladder[line][row] = true;
        }

        if (check()) {
            bw.write(String.valueOf(0));
        } else {
            for (int x = 1; x < n; ++x) {
                for (int y = 1; y <= h; ++y) {
                    if (!ladder[x][y] && !ladder[x - 1][y]) {
                        candidates.add(new Pos(x, y));
                    }
                }
            }

            func(0, 0);

            if (ans == Integer.MAX_VALUE) {
                bw.write(String.valueOf(-1));
            } else {
                bw.write(String.valueOf(ans));
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx) {
        if (idx == 3) {
            return;
        }

        for (int i = startIdx; i < candidates.size(); ++i) {
            Pos pos = candidates.get(i);
            ladder[pos.line][pos.row] = true;
            if (check()) {
                ans = Math.min(ans, idx + 1);
            }
            func(idx + 1, i + 1);
            ladder[pos.line][pos.row] = false;
        }
    }

    private static boolean check() {
        for (int x = 1; x <= n; ++x) {
            int num = x;
            for (int y = 0; y < h; ++y) {
                if (num < n && ladder[num][y + 1]) {
                    num++;
                } else if (ladder[num - 1][y + 1]) {
                    num--;
                }

            }

            if (x != num) {
                return false;
            }
        }

        return true;
    }

}
