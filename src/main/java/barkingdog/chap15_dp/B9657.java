package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9657 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        bw.write(n % 7 == 0 || n % 7 == 2 ? "CY" : "SK");

        bw.flush();
        bw.close();
        br.close();
    }

}
