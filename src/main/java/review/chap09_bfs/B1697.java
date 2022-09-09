package review.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1697 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int k;

    private static int[] dist;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new int[200_001];

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        dist[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == k) {
                bw.write(String.valueOf(dist[cur] - 1));
                bw.close();
                br.close();
                return;
            }

            for (int pos : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (pos < 0 || pos >= 200_001) {
                    continue;
                }

                if (dist[pos] != 0) {
                    continue;
                }

                q.offer(pos);
                dist[pos] = dist[cur] + 1;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
