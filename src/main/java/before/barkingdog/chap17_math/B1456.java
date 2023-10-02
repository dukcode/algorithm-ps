package before.barkingdog.chap17_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B1456 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static long a;
    private static long b;

    private static boolean[] isPrime = new boolean[10_000_001];
    private static final int MAX = 10_000_000;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        Arrays.fill(isPrime, true);

        List<Long> primes = new ArrayList<>();
        for (long i = 2; i <= MAX; ++i) {
            if (isPrime[(int) i]) {
                primes.add(i);
                for (long j = i * i; j <= MAX; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }

        System.out.println(primes);

        for (Long prime : primes) {
            if (prime * prime > b) {
                break;
            }

            for (double i = prime * prime; i <= b; i *= prime) {
                if (i >= a) {
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
