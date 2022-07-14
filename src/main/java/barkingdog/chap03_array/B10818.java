package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/**
 * B10818
 */
public class B10818 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[26];
        Arrays.fill(arr, 0);
        String str = br.readLine();
        for (int i = 0; i < str.length(); ++i) {
            int c = str.charAt(i) - 'a';
            arr[c]++;
        }

        for (int i = 0; i < arr.length; ++i) {
            bw.write(String.valueOf(arr[i]));
            bw.write(" ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}


