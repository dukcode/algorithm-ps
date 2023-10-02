package before.barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1929 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int m;
    private static int n;

    private static boolean[] notPrime;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        notPrime = new boolean[n + 1];

        notPrime[1] = true;
        for (long i = 2; i <= n; ++i) {
            if (notPrime[(int) i]) {
                continue;
            }

            for (long j = i * i; j <= n; j += i) {
                notPrime[(int) j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; ++i) {
            if (!notPrime[i]) {
                sb.append(i);
                sb.append('\n');
            }
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

}
