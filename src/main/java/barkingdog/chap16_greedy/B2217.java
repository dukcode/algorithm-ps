package barkingdog.chap16_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B2217 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] ropes;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);

        for (int i = n - 1; i >= 0; --i) {
            ans = Math.max(ans, ropes[i] * (n - i));
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
