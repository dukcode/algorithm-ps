package barkingdog.chap22_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class B1715 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while (pq.size() >= 2) {
            int n1 = pq.poll();
            int n2 = pq.poll();

            int sum = n1 + n2;
            ans += sum;
            pq.offer(sum);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
