package before.barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B1351 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static long n;
    private static int p;
    private static int q;
    private static Map<Long, Long> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cache.put(0L, 1L);

        bw.write(String.valueOf(func(n)));

        bw.flush();
        bw.close();
        br.close();
    }

    private static long func(long idx) {
        if (cache.containsKey(idx)) {
            return cache.get(idx);
        }

        long ret = func(idx / q) + func(idx / p);
        cache.put(idx, ret);
        return ret;
    }

}
