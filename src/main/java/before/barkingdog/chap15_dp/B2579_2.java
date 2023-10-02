package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2579_2 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int n;
    public static int[] arr;
    public static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        cache = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());

            if (i == 0) {
                cache[0] = arr[0];
            } else if (i == 1) {
                cache[1] = arr[1] + arr[0];
            } else if (i == 2) {
                cache[2] = arr[2] + Math.max(arr[1], arr[0]);
            } else {
                cache[i] = arr[i] + Math.max(arr[i - 1] + cache[i - 3], cache[i - 2]);
            }
        }

        bw.write(String.valueOf(cache[n - 1]));

        bw.flush();
        bw.close();
        br.close();
    }

}
