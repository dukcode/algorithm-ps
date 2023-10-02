package before.barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B11656 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        String[] suffixes = new String[word.length()];

        for (int i = 0; i < word.length(); ++i) {
            String suffix = word.substring(i);
            suffixes[i] = suffix;
        }

        Arrays.sort(suffixes);

        for (int i = 0; i < word.length(); ++i) {
            bw.write(suffixes[i]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
