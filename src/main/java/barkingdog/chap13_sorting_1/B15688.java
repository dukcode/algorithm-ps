package barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B15688 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static final int MAX = 1_000_000;
    private static int[] arr = new int[MAX * 2 + 1];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(br.readLine());
            arr[num + MAX]++;
        }

        for (int i = 0; i < MAX * 2 + 1; ++i) {
            for (int j = 0; j < arr[i]; ++j) {
                bw.write(String.valueOf(i - MAX));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
