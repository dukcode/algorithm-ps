package before.review.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10799 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();

        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (c == '(') {
                cnt++;
            } else {
                cnt--;
                if (line.charAt(i - 1) == '(') {
                    ans += cnt;
                } else {
                    ans++;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
