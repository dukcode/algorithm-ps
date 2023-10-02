package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B16987 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static final List<Egg> eggs = new ArrayList<>();

    private static int ans = 0;

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

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            eggs.add(new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        func(0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int idx) {
        ans = Math.max(ans, countBrokenEggs());
        if (idx == n) {
            return;
        }

        if (eggs.get(idx).durability <= 0) {
            func(idx + 1);
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (i == idx) {
                continue;
            }

            if (eggs.get(i).durability <= 0) {
                continue;
            }

            eggs.get(i).durability -= eggs.get(idx).weight;
            eggs.get(idx).durability -= eggs.get(i).weight;
            func(idx + 1);
            eggs.get(i).durability += eggs.get(idx).weight;
            eggs.get(idx).durability += eggs.get(i).weight;

        }
    }

    public static int countBrokenEggs() {
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            ret += eggs.get(i).durability <= 0 ? 1 : 0;
        }

        return ret;
    }

}
