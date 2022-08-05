package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15651 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int m;

    private static int[] arr;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        func(0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx) throws IOException {
        if (idx == m) {
            for (int i : arr) {
                bw.write(String.valueOf(i));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= n; ++i) {
            arr[idx] = i;
            func(idx + 1);
        }
    }

}
