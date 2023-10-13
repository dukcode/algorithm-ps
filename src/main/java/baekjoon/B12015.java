package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href=https://www.acmicpc.net/problem/12015>LINK</a>
 */
public class B12015 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(lisNlgN()));

        br.close();
        bw.close();
    }

    private static int lisNlgN() {
        int[] minValues = new int[n];

        int maxCnt = 0;
        for (int now = 0; now < n; ++now) {
            int cnt = lowerBound(minValues, 0, maxCnt, arr[now]);
            minValues[cnt] = arr[now];
            maxCnt = Math.max(maxCnt, cnt + 1);
        }

        return maxCnt;
    }

    private static int lowerBound(int[] arr, int st, int en, int target) {
        int pos = Arrays.binarySearch(arr, st, en, target);
        return (pos < 0 ? -(pos + 1) : pos);
    }


}
