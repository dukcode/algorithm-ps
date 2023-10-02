package before.beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B7579 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int[] memory;
    private static int[] cost;

    private static int[][] cache;

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memory = new int[n];
        cost = new int[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int costSum = 0;
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st1.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
            costSum += cost[i];
        }

        cache = new int[n][costSum + 1];
        for (int idx = 0; idx < n; idx++) {
            int mem = memory[idx];
            int c = cost[idx];

            for (int cost = 0; cost <= costSum; ++cost) {
                if (idx == 0) {
                    cache[idx][cost] = cost >= c ? mem : 0;
                } else {
                    if (cost >= c) {
                        cache[idx][cost] =
                                Math.max(cache[idx - 1][cost], cache[idx - 1][cost - c] + mem);
                    } else {
                        cache[idx][cost] = cache[idx - 1][cost];
                    }
                }

                if (cache[idx][cost] >= m) {
                    ans = Math.min(ans, cost);
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }


}
