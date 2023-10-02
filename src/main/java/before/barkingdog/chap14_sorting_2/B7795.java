package before.barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B7795 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;

    private static int n;
    private static int m;

    private static int[] arrA;
    private static int[] arrB;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arrA = new int[n];
            m = Integer.parseInt(st.nextToken());
            arrB = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrA);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrB);

            int ans = 0;
            int lastIdx = 0;
            for (int i = 0; i < n; ++i) {
                int a = arrA[i];

                while (lastIdx < m && arrB[lastIdx] < a) {
                    lastIdx++;
                }

                ans += lastIdx;
            }

            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
