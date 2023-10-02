package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 백트랙킹은 상태공간을 다루는 알고리즘이라는 것에 초첨을 맞추어 푸는게 옳다
// 이 문제에서의 상태를 결정하는 factor는  어디까지 더했는가(idx), 합(tot) 이다.
public class B1182_1 {

    static int n;
    static int sum;
    static int[] arr;

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

        int ans = func(0, 0);

        if (sum == 0) {
            bw.write(String.valueOf(ans - 1));  // 공집합 제거
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int func(int idx, int tot) {
        if (idx == n) {
            if (tot == sum) {
                return 1;
            }
            return 0;
        }

        int ans = 0;
        ans += func(idx + 1, tot);
        ans += func(idx + 1, tot + arr[idx]);

        return ans;
    }

}
