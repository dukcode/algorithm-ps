package barkingdog.chap22_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B13975 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int k;
    private static PriorityQueue<Long> q;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            k = Integer.parseInt(br.readLine());
            q = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                q.offer(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while (q.size() != 1) {
                long c1 = q.poll();
                long c2 = q.poll();

                long sum = c1 + c2;
                ans += sum;
                q.offer(sum);
            }

            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
