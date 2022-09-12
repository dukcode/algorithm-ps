package barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B6064 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;

    private static int m;
    private static int n;
    private static int x;
    private static int y;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if (x == m) {
                x = 0;
            }
            if (y == n) {
                y = 0;
            }

            int year = 0;
            for (int i = x; i <= lcm(m, n); i += m) {
                if (i == 0) {
                    continue;
                }

                if (i % n == y) {
                    year = i;
                    break;
                }
            }

            if (year == 0) {
                bw.write(String.valueOf(-1));
            } else {
                bw.write(String.valueOf(year));
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

}
