package review.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int l;
    private static int c;
    private static char[] arr;

    private static char[] ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        ans = new char[l];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        func(0, 0, 0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int startIdx, int numVowel) throws IOException {
        if (idx == l) {
            if (numVowel < 1 || l - numVowel < 2) {
                return;
            }

            for (int i = 0; i < l; i++) {
                bw.write(ans[i]);
            }
            bw.newLine();
            return;
        }

        for (int i = startIdx; i < c; ++i) {
            char ch = arr[i];
            ans[idx] = ch;
            if (isVowel(ch)) {
                func(idx + 1, i + 1, numVowel + 1);
            } else {
                func(idx + 1, i + 1, numVowel);
            }
        }
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

}
