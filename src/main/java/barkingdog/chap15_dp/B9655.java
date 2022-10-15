package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9655 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (Integer.parseInt(br.readLine()) % 2 == 1) {
            bw.write("SK");
        } else {
            bw.write("CY");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
