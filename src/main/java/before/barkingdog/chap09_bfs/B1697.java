package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[200001];
        Arrays.fill(arr, -1);

        Queue<Integer> q = new LinkedList<>();
        arr[n] = 0;
        q.offer(n);

        while (arr[x] == -1) {
            int pos = q.poll();
            int time = arr[pos];

            for (int next : new int[]{pos - 1, pos + 1, pos * 2}) {

                if (next < 0 || next > 200000) {
                    continue;
                }

                if (arr[next] != -1) {
                    continue;
                }

                arr[next] = time + 1;
                q.offer(next);
            }

        }

        bw.write(String.valueOf(arr[x]));

        bw.flush();
        bw.close();
        br.close();
    }

}
