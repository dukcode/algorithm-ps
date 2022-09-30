package barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B16401 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int m;
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int st = 0;
        int en = Arrays.stream(arr).max().getAsInt();

        while (st < en) {
            int mid = (st + en + 1) / 2;

            int numSnack = calculateNumSnack(mid);

            if (m <= numSnack) {
                st = mid;
            } else {
                en = mid - 1;
            }
        }

        bw.write(String.valueOf(st));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int calculateNumSnack(int length) {
        int ret = 0;
        for (int i = 0; i < n; ++i) {
            ret += arr[i] / length;
        }

        return ret;
    }

}
