package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2446 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n * 2 - 1; ++i) {
            int countSpace = i < n ? i : n * 2 - 2 - i;
            bw.write(" ".repeat(countSpace));
            bw.write("*".repeat(2 * n - 1 - 2 * countSpace));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
