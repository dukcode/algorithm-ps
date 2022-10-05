package barkingdog.chap22_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class B1927_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                Integer val = pq.poll();
                val = val == null ? 0 : val;
                bw.write(String.valueOf(val));
                bw.newLine();
                continue;
            }

            pq.add(num);

        }

        bw.flush();
        bw.close();
        br.close();
    }

}
