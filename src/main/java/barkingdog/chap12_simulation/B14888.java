package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B14888 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static int n;
    public static int[] arr;

    public static int[] operators = {0, 0, 0, 0}; // +, -, *, /
    public static int numOperators;
    public static int[] opIdxArr;

    private static int ansMax = Integer.MIN_VALUE;
    private static int ansMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        numOperators = n - 1;
        opIdxArr = new int[numOperators];

        func(0, arr[0]);

        bw.write(String.valueOf(ansMax));
        bw.newLine();
        bw.write(String.valueOf(ansMin));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int res) {
        if (idx == n - 1) {
            ansMax = Math.max(ansMax, res);
            ansMin = Math.min(ansMin, res);
        }

        for (int i = 0; i < 4; ++i) {
            if (operators[i] == 0) {
                continue;
            }

            operators[i]--;
            int tmp = res;

            switch (i) {
                case 0:
                    res += arr[idx + 1];
                    break;
                case 1:
                    res -= arr[idx + 1];
                    break;
                case 2:
                    res *= arr[idx + 1];
                    break;
                case 3:
                    res /= arr[idx + 1];
                    break;
                default:
                    break;
            }

            func(idx + 1, res);

            res = tmp;
            operators[i]++;
        }
    }

}
