package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B16987_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static final List<Egg> eggs = new ArrayList<>();
    private static int[] durability;
    private static int[] weight;

    private static int ans = 0;
    private static int cnt = 0;

    private static class Egg {

        public int durability;
        public int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        durability = new int[n];
        weight = new int[n];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            durability[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        func(0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int idx) {
        if (idx == n) {
            ans = Math.max(ans, cnt);
            return;
        }

        if (durability[idx] <= 0 || cnt == n - 1) {
            func(idx + 1);
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (i == idx || durability[i] <= 0) {
                continue;
            }

            durability[i] -= weight[idx];
            durability[idx] -= weight[i];
            if (durability[i] <= 0) {
                cnt++;
            }
            if (durability[idx] <= 0) {
                cnt++;
            }

            func(idx + 1);

            if (durability[i] <= 0) {
                cnt--;
            }
            if (durability[idx] <= 0) {
                cnt--;
            }
            durability[i] += weight[idx];
            durability[idx] += weight[i];


        }
    }

}
