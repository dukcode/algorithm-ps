package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B12852 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] count;
    private static int[] from;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        count = new int[n + 1];
        from = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            func(i + 1, i);
            func(i * 2, i);
            func(i * 3, i);
        }

        bw.write(String.valueOf(count[n]));
        bw.newLine();

        int i = n;
        while (i != 0) {
            bw.write(String.valueOf(i));
            bw.write(' ');
            i = from[i];
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int dest, int origin) {
        if (dest <= n) {
            if (count[dest] == 0 || count[dest] > count[origin] + 1) {
                count[dest] = count[origin] + 1;
                from[dest] = origin;
            }
        }
    }
}
