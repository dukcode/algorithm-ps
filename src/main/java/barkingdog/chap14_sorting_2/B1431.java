package barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B1431 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static String[] serials;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        serials = new String[n];
        for (int i = 0; i < n; ++i) {
            serials[i] = br.readLine();
        }

        Arrays.sort(serials, (s1, s2) -> {
            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }

            int sum1 = getNumSum(s1);
            int sum2 = getNumSum(s2);
            if (sum1 != sum2) {
                return sum1 - sum2;
            }

            return s1.compareTo(s2);

        });

        for (int i = 0; i < n; ++i) {
            bw.write(serials[i]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int getNumSum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);
            if (Character.isDigit(c)) {
                sum += c - '0';
            }
        }

        return sum;
    }
}
