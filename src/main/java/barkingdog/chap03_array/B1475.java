package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * B1475
 */
public class B1475 {

    private static int[] arr = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String roomNum = br.readLine();

        for (int i = 0; i < roomNum.length(); ++i) {
            int num = roomNum.charAt(i) - '0';
            arr[num]++;
        }

        int avg = (arr[6] + arr[9] + 1) / 2;
        arr[6] = avg;
        arr[9] = avg;

        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
        }

        bw.write(String.valueOf(max));

        bw.flush();
        bw.close();
        br.close();
    }
}


