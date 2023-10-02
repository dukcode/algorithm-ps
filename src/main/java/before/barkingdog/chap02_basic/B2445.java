package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2445 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 2 * n - 1; ++i) {
            int countStar = i < n ? i + 1 : 2 * n - 1 - i;
            int countSpace = 2 * n - 2 * countStar;

            String stars = "*".repeat(countStar);
            bw.write(stars);
            bw.write(" ".repeat(countSpace));
            bw.write(stars);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
