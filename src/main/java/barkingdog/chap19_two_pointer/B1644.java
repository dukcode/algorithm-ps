package barkingdog.chap19_two_pointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B1644 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static boolean[] primes;

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        if (n == 1) {
            bw.write('0');
            bw.close();
            br.close();
            return;
        }

        primes = new boolean[n + 1];
        primes[0] = primes[1] = false;
        Arrays.fill(primes, true);
        for (int i = 2; i * i <= n; ++i) {
            if (!primes[i]) {
                continue;
            }

            for (int j = i * i; j <= n; j += i) {
                primes[j] = false;
            }

        }

        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                continue;
            }

            list.add(i);
        }

        int st = 0;
        int en = 0;
        int sum = list.get(0);

        while (st < list.size()) {
            if (sum < n) {
                if (en == list.size() - 1) {
                    break;
                }
                sum += list.get(++en);
                continue;
            }

            if (sum == n) {
                ans++;
            }
            sum -= list.get(st++);

        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
