package before.barkingdog.chap19_two_pointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1806 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static int[] arr;

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int st = 0;
        int en = 0;
        int sum = arr[0];

        while (st < n && en < n) {
            if (sum < m) {
                if (en + 1 == n) {
                    break;
                }
                sum += arr[++en];
                continue;
            }

            ans = Math.min(ans, en - st + 1);
            sum -= arr[st++];
        }

        if (ans == Integer.MAX_VALUE) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
