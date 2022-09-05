package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B11659 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int[] sumArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sumArr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            sumArr[i] = sum;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(sumArr[end] - sumArr[start - 1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
