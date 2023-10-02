package before.barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B5648 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static long[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new long[n];

        int i = 0;
        while (st.hasMoreTokens()) {
            arr[i] = Long.parseLong(getReverse(st.nextToken()));
            i++;
        }

        String line = "";
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                arr[i] = Long.parseLong(getReverse(st.nextToken()));
                i++;
            }
        }

        Arrays.sort(arr);

        for (int idx = 0; idx < n; ++idx) {
            bw.write(String.valueOf(arr[idx]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static String getReverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
