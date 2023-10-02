package before.barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B11652 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    private static long[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        int maxCount = 1;
        int count = 1;
        long ans = arr[0];
        for (int i = 1; i < n; ++i) {
            if (arr[i - 1] == arr[i]) {
                count++;
            } else {
                if (maxCount < count) {
                    ans = arr[i - 1];
                    maxCount = count;
                }
                count = 1;
            }
        }

        if (count > maxCount) {
            ans = arr[n - 1];
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
