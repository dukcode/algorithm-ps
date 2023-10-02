package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class B14002_2 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static int[] arr;
    private static int[] prevIdx;
    private static int[] cache;
    private static int[] idxCache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        prevIdx = new int[n];
        cache = new int[n];
        idxCache = new int[n];

        st = new StringTokenizer(br.readLine());
        int size = 0;
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());

            int insertPoint = Arrays.binarySearch(cache, 0, size, arr[i]);
            if (insertPoint >= 0) {
                continue;
            }

            insertPoint = insertPoint * -1 - 1;
            if (insertPoint == size) {
                size++;
            }

            cache[insertPoint] = arr[i];
            idxCache[insertPoint] = i;
            prevIdx[i] = insertPoint == 0 ? -1 : idxCache[insertPoint - 1];
        }

        bw.write(String.valueOf(size));
        bw.newLine();

        int idx = idxCache[size - 1];
        Stack<Integer> stk = new Stack<>();
        while (idx != -1) {
            stk.push(arr[idx]);
            idx = prevIdx[idx];
        }

        while (!stk.isEmpty()) {
            bw.write(String.valueOf(stk.pop()));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
