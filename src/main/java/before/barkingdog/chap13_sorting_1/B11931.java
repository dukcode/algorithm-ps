package before.barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B11931 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static final int MAX = 1_000_000;
    private static List<Boolean> arr = new ArrayList<>(Collections.nCopies(2 * MAX + 1, false));

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(br.readLine());
            arr.set(num + MAX, true);
        }

        for (int i = MAX * 2; i >= 0; --i) {
            if (arr.get(i)) {
                bw.write(String.valueOf(i - MAX));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
