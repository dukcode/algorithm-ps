package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(mod(a, b, c)));

        bw.flush();
        bw.close();
        br.close();
    }

    public static long mod(long base, long pow, long divisor) {
        if (pow == 0) {
            return 1;
        }

        long value = mod(base, pow / 2, divisor);
        value = (value * value) % divisor;

        if (pow % 2 == 0) {
            return value;
        }

        return (value * base) % divisor;
    }

}
