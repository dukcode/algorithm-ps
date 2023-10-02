package before.barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B1012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        LinkedList<Integer> d = IntStream.rangeClosed(1, N).boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int ans = 0;
        while (M-- > 0) {
            int num = Integer.parseInt(st2.nextToken());
            int idx = d.indexOf(num);

            while (d.getFirst() != num) {
                if (idx < d.size() - idx) {
                    d.addLast(d.pollFirst());
                } else {
                    d.addFirst(d.pollLast());
                }
                ans++;
            }
            d.pollFirst();
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }


}
