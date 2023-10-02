package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B15654 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static int m;

    private static int[] ans;
    private static boolean[] isUsed;

    private static List<Integer> arr;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new int[m];
        isUsed = new boolean[n];

        arr = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        func(0, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int count) throws IOException {
        if (count == m) {
            for (Integer num : ans) {
                bw.write(String.valueOf(num));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (isUsed[i]) {
                continue;
            }

            isUsed[i] = true;
            ans[idx] = arr.get(i);
            func(idx + 1, count + 1);
            isUsed[i] = false;
        }
    }

}
