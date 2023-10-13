package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * <a href=https://www.acmicpc.net/problem/12738>LINK</a>
 */
public class B12738 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;

    private static int[] cache;

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

        cache = new int[n + 1];
        bw.write(String.valueOf(lisTopDown(-1) - 1));

        bw.close();
        br.close();
    }

    private static int lisTopDown(int start) {
        if (cache[start + 1] != 0) {
            return cache[start + 1];
        }

        cache[start + 1] = 1;
        for (int next = start + 1; next < n; ++next) {
            if (start < 0 || arr[start] < arr[next]) {
                cache[start + 1] = Math.max(cache[start + 1], lisTopDown(next) + 1);
            }
        }

        return cache[start + 1];
    }


}
