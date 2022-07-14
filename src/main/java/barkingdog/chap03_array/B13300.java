package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**
 * B13300
 */
public class B13300 {

    private static int[] girls = new int[7];
    private static int[] boys = new int[7];

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        for (int i = 0; i < N; ++i) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int sex = Integer.valueOf(st2.nextToken());
            int grade = Integer.valueOf(st2.nextToken());

            if (sex == 0) {
                girls[grade]++;
            } else {
                boys[grade]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= 6; ++i) {
            ans += ((girls[i] + K - 1) / K) + ((boys[i] + K - 1) / K);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}


