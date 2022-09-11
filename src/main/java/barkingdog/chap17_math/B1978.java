package barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1978 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());

            if (isPrime(num)) {
                ans++;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}
