package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B6603 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int k;
    private static int[] s;

    private static int[] arr;
    private static int[] ans;


    public static void main(String[] args) throws IOException {

        while (true) {
            if (input()) {
                break;
            }

            func(-1, 0);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int lastIdx, int idx) throws IOException {
        if (idx == 6) {
            for (int i = 0; i < 6; ++i) {
                bw.write(String.valueOf(ans[i]));
                bw.write(' ');
            }
            bw.newLine();
            return;
        }

        for (int i = lastIdx + 1; i < k; ++i) {
            ans[idx] = s[i];
            func(i, idx + 1);
        }
    }

    private static boolean input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        if (k == 0) {
            return true;
        }
        s = new int[k];
        ans = new int[6];
        for (int i = 0; i < k; ++i) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        return false;
    }

}
