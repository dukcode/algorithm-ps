package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2576 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int oddSum;
    private static int oddMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 7; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num % 2 == 1) {
                oddSum += num;
                oddMin = Math.min(oddMin, num);
            }
        }

        if (oddSum == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(oddSum));
            bw.newLine();
            bw.write(String.valueOf(oddMin));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
