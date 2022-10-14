package barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B14002 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;
    private static int[] cache;
    private static int[] prevIdxes;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        cache = new int[n + 1];
        prevIdxes = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());

            int val = 0;
            int prevIdx = 0;

            for (int j = 0; j < i; ++j) {
                if (arr[j] < arr[i] && cache[j] >= val) {
                    val = cache[j];
                    prevIdx = j;
                }

            }

            cache[i] = val + 1;
            prevIdxes[i] = prevIdx;
        }

        int maxIdx = 1;
        int maxLength = cache[1];
        for (int i = 1; i <= n; ++i) {
            if (maxLength < cache[i]) {
                maxLength = cache[i];
                maxIdx = i;
            }
        }

        ans = maxLength;

        int idx = maxIdx;
        Deque<Integer> dq = new LinkedList<>();
        while (idx != 0) {
            dq.offer(arr[idx]);
            idx = prevIdxes[idx];
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        while (!dq.isEmpty()) {
            bw.write(String.valueOf(dq.pollLast()));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
