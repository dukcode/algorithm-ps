package barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B1181 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static String[] words;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        words = new String[n];

        for (int i = 0; i < n; ++i) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (w1, w2) -> {
            if (w1.length() != w2.length()) {
                return w1.length() - w2.length();
            }

            return w1.compareTo(w2);
        });

        words = Arrays.stream(words).distinct().toArray(String[]::new);

        for (String word : words) {
            bw.write(word);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
