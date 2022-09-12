package review.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1629 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int a;
    private static int b;
    private static int c;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(func(a, b, c)));

        bw.flush();
        bw.close();
        br.close();
    }

    public static long func(int base, int pow, int div) {
        // base case
        if (pow == 0) {
            return 1L;
        }

        long value = func(base, pow / 2, div);

        value = (value * value) % div;

        return pow % 2 == 0 ? value % div : (value * base) % div;
    }

}
