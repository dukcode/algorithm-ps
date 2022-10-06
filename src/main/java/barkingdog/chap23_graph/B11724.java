package barkingdog.chap23_graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11724 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static boolean[][] graph;
    private static boolean[] visited;

    private static Queue<Integer> q = new LinkedList<>();

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s][e] = true;
            graph[e][s] = true;
        }

        for (int idx = 1; idx <= n; ++idx) {
            if (visited[idx]) {
                continue;
            }

            q.offer(idx);
            visited[idx] = true;
            ans++;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int i = 1; i <= n; i++) {
                    if (visited[i] || !graph[cur][i]) {
                        continue;
                    }

                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
