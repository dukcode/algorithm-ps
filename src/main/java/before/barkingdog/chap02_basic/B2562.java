package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int maxValue = 0;
        int maxIdx = 0;
        for (int idx = 1; idx <= 9; ++idx) {
            int num = Integer.parseInt(br.readLine());
            if (maxValue < num) {
                maxValue = num;
                maxIdx = idx;
            }
        }

        bw.write(String.valueOf(maxValue));
        bw.newLine();
        bw.write(String.valueOf(maxIdx));

        bw.flush();
        bw.close();
        br.close();
    }

}
