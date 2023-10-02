package before.barkingdog.chap21_bst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B1202 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static class Jewel {

        int weight;
        int cost;

        public Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    private static int n;
    private static int k;

    private static Jewel[] jewels;
    private static TreeSet<Integer> bags = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewels = new Jewel[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, cost);
        }

        Arrays.sort(jewels, (j1, j2) -> j2.cost - j1.cost);

        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        int value = 0;
        for (int i = 0; i < n; ++i) {
            Jewel jewel = jewels[i];

            Integer bag = bags.ceiling(jewel.weight);
            if (bag == null) {
                continue;
            }

            value += jewel.cost;
            bags.remove(bag);
        }

        bw.write(String.valueOf(value));

        bw.flush();
        bw.close();
        br.close();
    }

}
