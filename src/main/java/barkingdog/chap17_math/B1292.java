package barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1292 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] arr;

    private static int a;
    private static int b;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        arr = new int[1001];
        int idx = 1;
        int num = 1;
        Loop:
        while (true) {
            for (int i = 0; i < num; ++i) {
                arr[idx++] = num;
                if (idx == 1001) {
                    break Loop;
                }
            }
            num++;
        }
        System.out.println(Arrays.toString(arr));

        int sum = 0;
        for (int i = a; i <= b; ++i) {
            sum += arr[i];
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
        br.close();
    }

}
