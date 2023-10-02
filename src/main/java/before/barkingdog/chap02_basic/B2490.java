package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2490 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 3; ++i) {
            st = new StringTokenizer(br.readLine());

            int ans = 0;
            for (int j = 0; j < 4; ++j) {
                ans += Integer.parseInt(st.nextToken());
            }

            switch (ans) {
                case 3:     // 도
                    bw.write('A');
                    break;
                case 2:     // 개
                    bw.write('B');
                    break;
                case 1:     // 걸
                    bw.write('C');
                    break;
                case 0:     // 윷
                    bw.write('D');
                    break;
                case 4:     // 모
                    bw.write('E');
                    break;
                default:
                    break;
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
