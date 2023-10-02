package before.barkingdog.chap16_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// O(n^2) 풀이 - 시간초과
public class B11047 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int target;

    private static int[] coins;
    private static int[] count;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        coins = new int[n];
        count = new int[target + 1];
        Arrays.fill(count, Integer.MAX_VALUE);

        for (int i = 0; i < n; ++i) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;

            if (coin < target + 1) {
                count[coin] = 1;
            }
        }

        count[0] = 0;
        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - coins[j] >= 0 && count[i - coins[j]] != Integer.MAX_VALUE) {
                    count[i] = Math.min(count[i], count[i - coins[j]] + 1);
                }
            }
        }
        bw.write(String.valueOf(count[target]));

        bw.flush();
        bw.close();
        br.close();
    }

}
