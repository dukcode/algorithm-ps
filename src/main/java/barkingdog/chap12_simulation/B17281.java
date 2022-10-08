package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B17281 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] playerResult;
    private static int[] order;
    private static boolean[] taken;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        playerResult = new int[n][10];
        for (int inning = 0; inning < n; ++inning) {
            st = new StringTokenizer(br.readLine());
            for (int player = 1; player <= 9; ++player) {
                playerResult[inning][player] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[9];
        order[3] = 1;
        taken = new boolean[10];
        func(0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx) {
        if (idx == 9) {
            ans = Math.max(ans, calculateScore());
            return;
        }

        if (idx == 3) {
            func(idx + 1);
            return;
        }

        for (int player = 2; player <= 9; ++player) {
            if (taken[player]) {
                continue;
            }

            order[idx] = player;
            taken[player] = true;
            func(idx + 1);
            taken[player] = false;
        }
    }

    private static int calculateScore() {
        int score = 0;
        int o = 0;

        for (int inning = 0; inning < n; ++inning) {
            boolean[] base = {false, false, false, false};

            int outCount = 0;
            while (outCount < 3) {
                int player = order[o];
                int move = playerResult[inning][player];
                base[0] = true;

                if (move == 0) {
                    outCount++;
                } else {
                    for (int i = 3; i >= 0; --i) {
                        if (!base[i]) {
                            continue;
                        }

                        if (i + move >= 4) {
                            score++;
                        } else {
                            base[i + move] = true;
                        }
                        base[i] = false;
                    }
                }

                o = (o + 1) % 9;
            }
        }
        return score;
    }

}
