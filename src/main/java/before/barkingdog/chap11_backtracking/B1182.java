package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1182 {

    static int n;
    static int sum;
    static int[] arr;

    static int partialSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        sum = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = func(0);

        if (sum == 0) {
            bw.write(String.valueOf(ans - 1));  // 공집합 제거
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int func(int idx) {
        if (idx == n) {
            if (partialSum == sum) {
                return 1;
            }
            return 0;
        }

        int ans = 0;
        ans += func(idx + 1);
        partialSum += arr[idx];
        ans += func(idx + 1);
        partialSum -= arr[idx];

        return ans;
    }

}
