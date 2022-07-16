package barkingdog.chap07_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] charArr = br.readLine().toCharArray();

        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < charArr.length; ++i) {
            char c = charArr[i];

            if (c == '(') {
                cnt++;
                continue;
            }

            cnt--;
            if (charArr[i - 1] == '(') {
                ans += cnt;
            } else {
                ans++;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
