package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B16986 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int k;
    private static int[][] counters;

    private static final int WIN = 2;
    private static final int TIE = 1;
    private static final int LOSE = 0;

    private static int[][] rsp;

    private static boolean[] taken;

    private static boolean possible;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt((st.nextToken()));

        if (n < k) {
            bw.write(String.valueOf(0));
            bw.close();
            br.close();
            return;
        }

        counters = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                counters[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        rsp = new int[3][];

        rsp[1] = new int[20];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; ++i) {
            rsp[1][i] = Integer.parseInt(st.nextToken()) - 1;
        }

        rsp[2] = new int[20];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; ++i) {
            rsp[2][i] = Integer.parseInt(st.nextToken()) - 1;
        }

        rsp[0] = new int[n];
        taken = new boolean[n];
        func(0);

        bw.write(String.valueOf(possible ? 1 : 0));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx) {
        if (possible) {
            return;
        }

        if (idx == n) {
            int[] numWin = new int[3];
            int[] pos = new int[3];

            int first = 0;
            int second = 1;
            Loop:
            while (pos[0] < n) {
                int result = counters[rsp[first][pos[first]++]][rsp[second][pos[second]++]];
                int next = 3 - first - second;

                if (result == WIN) {    // first 이김
                    numWin[first]++;
                    second = next;
                } else if (result == TIE) {                // second 이김, 비김 (second 올라감)
                    if (first < second) {
                        first = next;
                        numWin[second]++;
                    } else {
                        second = next;
                        numWin[first]++;
                    }
                } else {
                    numWin[second]++;
                    first = next;
                }

                for (int i = 0; i < 3; ++i) {
                    if (numWin[i] >= k) {
                        break Loop;
                    }
                }

            }

            if (numWin[0] >= k) {
                possible = true;
            }

            return;
        }

        for (int i = 0; i < n; ++i) {
            if (taken[i]) {
                continue;
            }

            taken[i] = true;
            rsp[0][idx] = i;
            func(idx + 1);
            taken[i] = false;
        }
    }

}
