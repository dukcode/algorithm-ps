package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B9084 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int n;
    private static int m;
    private static int[] arr;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            m = Integer.parseInt(br.readLine());
            cache = new int[m + 1];
            cache[0] = 1;
            for (int coin : arr) {
                for (int i = 1; i <= m; ++i) {
                    if (i - coin < 0) {
                        continue;
                    }

                    cache[i] += cache[i - coin];
                }
            }

            bw.write(String.valueOf(cache[m]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
