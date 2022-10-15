package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B11660 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static int[][] cache;

    private static int y1;
    private static int x1;
    private static int y2;
    private static int x2;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cache = new int[n + 1][n + 1];
        for (int y = 1; y <= n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= n; ++x) {
                int num = Integer.parseInt(st.nextToken());
                cache[y][x] = cache[y - 1][x] + cache[y][x - 1] - cache[y - 1][x - 1] + num;
            }
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            y1 = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(
                    cache[y2][x2] - cache[y1 - 1][x2] - cache[y2][x1 - 1] + cache[y1 - 1][x1 - 1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
