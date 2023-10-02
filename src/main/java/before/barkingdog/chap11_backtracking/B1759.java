package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int l;
    private static int c;

    private static char[] arr;
    private static char[] ans;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        ans = new char[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; ++i) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        func(-1, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int lastIdx, int idx) throws IOException {
        if (idx == l) {
            boolean hasVowel = false;
            int count = 0;
            for (int i = 0; i < l; ++i) {
                char ch = ans[i];
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    hasVowel = true;
                } else {
                    count++;
                }

                if (count >= 2 && hasVowel) {
                    break;
                }
            }

            if (hasVowel && count >= 2) {
                bw.write(ans);
                bw.newLine();
            }
            return;
        }

        for (int i = lastIdx + 1; i < c; ++i) {
            ans[idx] = arr[i];
            func(i, idx + 1);
        }
    }

}
