package barkingdog.chap22_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class B11286 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>(
            (i1, i2) -> {
                int ai1 = Math.abs(i1);
                int ai2 = Math.abs(i2);

                if (ai1 == ai2) {
                    return i1 - i2;
                }

                return ai1 - ai2;
            });

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (pq.isEmpty()) {
                    bw.write(String.valueOf(0));
                } else {
                    bw.write(String.valueOf(pq.poll()));
                }
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
