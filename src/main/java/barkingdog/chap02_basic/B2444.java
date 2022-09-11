package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2444 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = n - 1; i > 0; --i) {
            bw.write(" ".repeat(i));
            bw.write("*".repeat(2 * (n - i - 1) + 1));
            bw.newLine();
        }

        for (int i = 0; i < n; i++) {
            bw.write(" ".repeat(i));
            bw.write("*".repeat(2 * (n - i - 1) + 1));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
