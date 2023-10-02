package before.barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1654 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int k;
    private static int n;
    private static int[] lanCables;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lanCables = new int[k];
        for (int i = 0; i < k; i++) {
            lanCables[i] = Integer.parseInt(br.readLine());
        }

        long st = 1L;
        long en = 0x7FFFFFFFL;

        while (st < en) {
            long mid = (st + en + 1) / 2L;

            int numCable = calculateNumCable(mid);
            if (n <= numCable) {
                st = mid;
            } else {
                en = mid - 1L;
            }
        }

        bw.write(String.valueOf(st));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int calculateNumCable(long length) {
        int ret = 0;
        for (int i = 0; i < k; ++i) {
            ret += lanCables[i] / length;
        }

        return ret;
    }

}
