package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/**
 * B2577
 */
public class B2577 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int product = 1;
        for (int i = 0; i < 3; ++i) {
            product *= Integer.valueOf(br.readLine());
        }

        String result = String.valueOf(product);
        int[] arr = new int[10];
        Arrays.fill(arr, 0);
        for (int i = 0; i < result.length(); ++i) {
            int num = result.charAt(i) - '0';
            arr[num]++;
        }

        for (int i = 0; i < arr.length; ++i) {
            bw.write(String.valueOf(arr[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}


