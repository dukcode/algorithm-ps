package before.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1074 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(getCount(n, r, c)));

        bw.flush();
        bw.close();
        br.close();
    }

    public static int getCount(int n, int r, int c) {
        if (n == 0) {
            return 0;
        }

        int half = 1 << (n - 1);

        if (r < half && c < half) {
            return getCount(n - 1, r, c);
        }
        if (r < half && c >= half) {
            return half * half + getCount(n - 1, r, c - half);
        }

        if (r >= half && c < half) {
            return 2 * half * half + getCount(n - 1, r - half, c);
        }

        return 3 * half * half + getCount(n - 1, r - half, c - half);
    }

}
