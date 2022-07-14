package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * B10871
 */
public class B10871 {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int max = arr[1];

        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        for (int num : nums) {
            if (num < max) {
                bw.write(String.valueOf(num));
                bw.write(" ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
