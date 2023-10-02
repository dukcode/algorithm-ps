package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class B14002_1 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;
    private static int[] length;
    private static int[] cache;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        length = new int[n + 1];
        cache = new int[n + 1];

        int idx = 1;
        int maxLength = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());

            int lowerIdx = Arrays.binarySearch(cache, 0, idx, arr[i]);
            lowerIdx = lowerIdx < 0 ? lowerIdx * -1 - 1 : lowerIdx;

            if (lowerIdx == idx) {
                idx++;
            }
            cache[lowerIdx] = arr[i];
            length[i] = lowerIdx;
            maxLength = Math.max(maxLength, length[i]);

        }

        Stack<Integer> stk = new Stack<>();
        for (int i = n; i > 0; --i) {
            if (length[i] == maxLength) {
                stk.push(arr[i]);
                maxLength--;
            }
        }

        bw.write(String.valueOf(idx - 1));
        bw.newLine();

        while (!stk.isEmpty()) {
            bw.write(String.valueOf(stk.pop()));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
