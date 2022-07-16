package barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B11003 {

    // D_i = A_(i-L+1) ~ A_i
    // ex) i = 5, L = 3
    // D_5 = A_3 ~ A_5

    public static class MyNum {

        int num;
        int idx;

        public MyNum(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int L = Integer.parseInt(st1.nextToken());

        Deque<MyNum> d = new ArrayDeque<>();
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(st2.nextToken());

            while (!d.isEmpty() && d.getLast().num >= num) {
                d.pollLast();
            }

            d.offerLast(new MyNum(num, i));

            if (d.getFirst().idx < i - L + 1) {
                d.pollFirst();
            }

            bw.write(String.valueOf(d.getFirst().num));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
