package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2480 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] dice = new int[7];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; ++i) {
            int num = Integer.parseInt(st.nextToken());
            dice[num]++;
        }

        int ans = 0;
        for (int i = 1; i <= 6; ++i) {
            if (dice[i] == 3) {
                ans = 10000 + i * 1000;
                break;
            }

            if (dice[i] == 2) {
                ans = 1000 + i * 100;
                break;
            }

            if (dice[i] == 1) {
                ans = i * 100;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
