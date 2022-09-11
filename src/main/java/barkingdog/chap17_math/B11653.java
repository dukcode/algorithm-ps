package barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B11653 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 2; i * i <= n; ++i) {
            while (n % i == 0) {
                n /= i;
                bw.write(String.valueOf(i));
                bw.newLine();
            }

        }

        if (n != 1) {
            bw.write(String.valueOf(n));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
