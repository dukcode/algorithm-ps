package barkingdog.chap21_bst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B21939 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static class Problem {

        int p;
        int l;

        public Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        TreeSet<Problem> treeSet = new TreeSet<>((p1, p2) -> {
            if (p1.l == p2.l) {
                return p1.p - p2.p;
            }
            return p1.l - p2.l;
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            treeSet.add(new Problem(p, l));
            map.put(p, l);
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "add":
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    map.put(p, l);
                    treeSet.add(new Problem(p, l));
                    break;
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    Problem problem = x == 1 ? treeSet.last() : treeSet.first();
                    bw.write(String.valueOf(problem.p));
                    bw.newLine();
                    break;
                case "solved":
                    int num = Integer.parseInt(st.nextToken());
                    treeSet.remove(new Problem(num, map.get(num)));
                    map.remove(num);
                    break;
                default:
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
