package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15649 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int m;

    static int[] arr;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        isUsed = new boolean[n + 1];

        func(0);

        bw.flush();
        bw.close();
        br.close();
    }

    // idx번 째 수를 결정
    private static void func(int idx) throws IOException {
        if (idx == m) {
            for (int i = 0; i < m; ++i) {
                bw.write(String.valueOf(arr[i]));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= n; ++i) {
            if (isUsed[i]) {
                continue;
            }

            arr[idx] = i;
            isUsed[i] = true;
            func(idx + 1);
            isUsed[i] = false;
        }
    }
}
