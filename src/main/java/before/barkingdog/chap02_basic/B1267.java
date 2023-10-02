package before.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1267 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int y;
    private static int m;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int time = Integer.parseInt(st.nextToken());
            y += 10 * (time / 30 + 1);
            m += 15 * (time / 60 + 1);
        }

        if (y == m) {
            bw.write("Y M ");
            bw.write(String.valueOf(y));
        } else if (y < m) {
            bw.write("Y ");
            bw.write(String.valueOf(y));
        } else {
            bw.write("M ");
            bw.write(String.valueOf(m));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
