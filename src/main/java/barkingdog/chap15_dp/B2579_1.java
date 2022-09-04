package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2579_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int n;
    public static int total;
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
            if (i <= 2) {
                cache[i] = arr[i];
            }
            total += arr[i];
        }

        if (n <= 2) {
            bw.write(String.valueOf(total));
            bw.close();
            br.close();
            return;
        }

        for (int i = 3; i < n; ++i) {
            cache[i] = arr[i] + Math.min(cache[i - 2], cache[i - 3]);
        }

        bw.write(String.valueOf(total - Math.min(cache[n - 2], cache[n - 3])));

        bw.flush();
        bw.close();
        br.close();
    }

}
