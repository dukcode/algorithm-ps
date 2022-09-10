package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class B10804 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] cards;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cards = IntStream.rangeClosed(0, 20).toArray();

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            flip(start, end);
        }

        for (int i = 1; i <= 20; i++) {
            bw.write(String.valueOf(cards[i]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void flip(int start, int end) {
        while (start < end) {
            int tmp = cards[start];
            cards[start] = cards[end];
            cards[end] = tmp;

            start++;
            end--;
        }
    }
}
