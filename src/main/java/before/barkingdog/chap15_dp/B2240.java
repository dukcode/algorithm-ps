package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2240 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int w;
    private static int[] arr;

    private static int[][] cache;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        arr = new int[t + 1];
        for (int i = 1; i <= t; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        cache = new int[w + 2][t + 1];  // move, time (move == 1 -> 안움직임, time 1부터 시작)
        for (int move = 1; move <= w + 1; ++move) {
            for (int time = 1; time <= t; ++time) {
                cache[move][time] = arr[time] == (move - 1) % 2 + 1 ? 1 : 0;
                cache[move][time] += Math.max(cache[move - 1][time - 1], cache[move][time - 1]);
            }
        }

        for (int move = 1; move <= w + 1; ++move) {
            ans = Math.max(ans, cache[move][t]);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
