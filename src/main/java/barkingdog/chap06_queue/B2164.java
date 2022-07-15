package barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B2164 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = IntStream.rangeClosed(1, N).boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        int ans = 0;
        while (true) {
            ans = q.poll();
            if (q.isEmpty()) {
                break;
            }
            q.offer(q.poll());
        }

        bw.write(String.valueOf(ans));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }


}
