package before.barkingdog.chap24_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11725 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static boolean[][] tree;

    private static int[] parents;

    private static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        tree = new boolean[n + 1][n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            tree[first][second] = true;
            tree[second][first] = true;
        }

        q.offer(1);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next = 1; next <= n; next++) {
                if (!tree[cur][next] || parents[next] != 0) {
                    continue;
                }

                q.offer(next);
                parents[next] = cur;
            }
        }

        for (int i = 2; i <= n; i++) {
            bw.write(String.valueOf(parents[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
