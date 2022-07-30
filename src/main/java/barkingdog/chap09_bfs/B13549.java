package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13549 {

    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int size = Math.max(n + 1, 2 * k + 1);

        dist = new int[size];
        dist[n] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int[] posArr = new int[]{cur + 1, cur - 1, 2 * cur};
            for (int i = 0; i < 3; ++i) {
                int pos = posArr[i];

                // range check
                if (pos < 0 || pos >= size) {
                    continue;
                }

                // dist check
                if (i == 2) {
                    if (dist[pos] != 0 && dist[pos] <= dist[cur]) {
                        continue;
                    }
                    dist[pos] = dist[cur];
                } else {
                    if (dist[pos] != 0 && dist[pos] <= dist[cur] + 1) {
                        continue;
                    }
                    dist[pos] = dist[cur] + 1;
                }

                q.offer(pos);
            }
        }

        System.out.println(Arrays.toString(dist));
        bw.write(String.valueOf(dist[k] - 1));

        bw.flush();
        bw.close();
        br.close();
    }

}
