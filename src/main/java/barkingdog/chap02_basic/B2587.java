package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B2587 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int sum;
    private static int[] arr = new int[5];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        Arrays.sort(arr);

        bw.write(String.valueOf(sum / 5));
        bw.newLine();
        bw.write(String.valueOf(arr[2]));

        bw.flush();
        bw.close();
        br.close();
    }

}
