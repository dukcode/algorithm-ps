package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B11052 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;

    private static int[] cache;
    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        cache = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            cache[i] = arr[i];

            for (int j = 1; j <= i / 2; ++j) {
                cache[i] = Math.max(cache[i], cache[i - j] + cache[j]);
            }
            ans = Math.max(ans, cache[i]);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
