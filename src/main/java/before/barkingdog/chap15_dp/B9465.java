package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B9465 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int n;

    private static int[][] arr;
    private static int[][] cache;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            arr = new int[2][n + 1];
            cache = new int[2][n + 1];

            for (int i = 0; i < 2; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; ++j) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = Math.max(arr[0][1], arr[1][1]);
            cache[0][1] = arr[0][1];
            cache[1][1] = arr[1][1];
            for (int i = 2; i <= n; ++i) {
                int nonSelect = Math.max(cache[0][i - 2], cache[1][i - 2]);

                cache[0][i] = Math.max(nonSelect, cache[1][i - 1]) + arr[0][i];
                cache[1][i] = Math.max(nonSelect, cache[0][i - 1]) + arr[1][i];

                ans = Math.max(ans, Math.max(cache[0][i], cache[1][i]));
            }

            bw.write(String.valueOf(ans));
            bw.newLine();


        }

        bw.flush();
        bw.close();
        br.close();
    }

}
