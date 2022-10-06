package barkingdog.chap25_topology_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2623 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] inDegree;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        inDegree = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            int from = 0;
            int to = 0;
            for (int j = 0; j < num; j++) {
                from = to;
                to = Integer.parseInt(st.nextToken());

                if (from == 0) {
                    continue;
                }
                graph.get(from).add(to);
                inDegree[to]++;
            }
        }

        bw.write(bfs());

        bw.flush();
        bw.close();
        br.close();
    }

    private static String bfs() throws IOException {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] != 0) {
                continue;
            }

            q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            sb.append(cur);
            sb.append('\n');

            for (int next : graph.get(cur)) {
                inDegree[next]--;

                if (inDegree[next] != 0) {
                    continue;
                }

                q.offer(next);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return "0";
            }
        }

        return sb.toString();
    }


}
