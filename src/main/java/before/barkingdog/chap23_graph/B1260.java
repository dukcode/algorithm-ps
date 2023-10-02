package before.barkingdog.chap23_graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1260 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int v;

    private static boolean[][] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s][e] = true;
            graph[e][s] = true;
        }

        dfs();
        bw.newLine();
        bfs();

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() throws IOException {
        visited = new boolean[n + 1];

        Queue<Integer> q = new LinkedList<>();
        visited[v] = true;
        q.offer(v);

        while (!q.isEmpty()) {
            int cur = q.poll();

            bw.write(String.valueOf(cur));
            bw.write(' ');
            for (int next = 1; next <= n; ++next) {
                if (visited[next] || !graph[cur][next]) {
                    continue;
                }

                visited[next] = true;
                q.offer(next);
            }
        }
    }

    private static void dfs() throws IOException {
        visited = new boolean[n + 1];

        Stack<Integer> stk = new Stack<>();
        stk.push(v);

        while (!stk.isEmpty()) {
            int cur = stk.pop();

            if (!visited[cur]) {
                bw.write(String.valueOf(cur));
                bw.write(' ');
            }
            visited[cur] = true;
            for (int next = n; next >= 0; --next) {
                if (visited[next] || !graph[cur][next]) {
                    continue;
                }

                stk.push(next);
            }
        }
    }

}
