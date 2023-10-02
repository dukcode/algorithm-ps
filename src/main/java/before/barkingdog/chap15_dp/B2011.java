package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2011 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static char[] arr;
    private static int[] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = br.readLine().toCharArray();
        if (arr[0] == '0') {
            bw.write('0');
            bw.close();
            br.close();
            return;
        }

        cache = new int[arr.length + 1];

        cache[0] = 1;
        cache[1] = 1;
        boolean impossible = false;
        for (int i = 2; i <= arr.length; ++i) {
            int num2digit = 10 * (arr[i - 2] - '0') + arr[i - 1] - '0';
            int num = arr[i - 1] - '0';

            impossible = true;

            if (10 <= num2digit && num2digit <= 26) {
                cache[i] += cache[i - 2];
                impossible = false;
            }

            if (num > 0) {
                cache[i] += cache[i - 1];
                impossible = false;
            }

            cache[i] %= 1_000_000;

            if (impossible) {
                break;
            }
        }

        if (impossible) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(cache[arr.length]));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
