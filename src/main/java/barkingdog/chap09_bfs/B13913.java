package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13913 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int k;
    private static int[] dist;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int range = Math.max(n, k) * 2 + 1;
        dist = new int[range];

        Queue<Integer> q = new LinkedList<>();
        dist[n] = 1;
        q.offer(n);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next >= range) {
                    continue;
                }

                if (dist[next] != 0) {
                    continue;
                }

                if (next == k) {
                    dist[next] = dist[cur] + 1;
                    ans = dist[cur];
                    break;
                }

                dist[next] = dist[cur] + 1;
                q.offer(next);
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        List<Integer> route = new ArrayList<>();

        int now = k;
        route.add(now);
        Loop:
        while (now != n) {
            for (int before : new int[]{now - 1, now + 1, now % 2 == 0 ? now / 2 : -1}) {
                if (before < 0 || before >= range) {
                    continue;
                }

                if (dist[before] == dist[now] - 1) {
                    route.add(before);
                    now = before;
                    continue Loop;
                }
            }
        }

        for (int i = route.size() - 1; i >= 0; --i) {
            bw.write(String.valueOf(route.get(i)));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
