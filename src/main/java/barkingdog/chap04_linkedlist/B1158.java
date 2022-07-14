package barkingdog.chap04_linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B1158 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = IntStream.rangeClosed(1, N).boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        StringJoiner sj = new StringJoiner(", ", "<", ">");
        int count = 0;
        while (!q.isEmpty()) {
            count++;
            int num = q.poll();
            if (count % K == 0) {
                sj.add(String.valueOf(num));
            } else {
                q.offer(num);
            }
        }
        bw.write(sj.toString());

        bw.flush();
        bw.close();
        br.close();
    }


    public static class Node {

        int value;
        Node next;


    }
}


