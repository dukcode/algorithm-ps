package before.restart.jongman.chap08_dynamic_programming;

import java.io.*;
import java.util.Arrays;

public class Wildcard_1 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int c;
    private static String pattern;
    private static int n;
    private static String word;

    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("input.txt"));
        // br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            pattern = br.readLine();
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                word = br.readLine();
                initCache();
                if (match(0, 0) == 1) {
                    bw.write(word);
                    bw.newLine();
                }
            }
        }

        br.close();
        bw.close();
    }

    private static void initCache() {
        cache = new int[pattern.length() + 1][word.length() + 1];
        for (int i = 0; i <= pattern.length(); i++) {
            Arrays.fill(cache[i], -1);
        }
    }

    private static int match(int p, int w) {

        if (cache[p][w] != -1) {
            return cache[p][w];
        }

        if (p < pattern.length() && w < word.length()
                && (pattern.charAt(p) == '?' || pattern.charAt(p) == word.charAt(w))) {
            return cache[p][w] = match(p + 1, w + 1);
        }

        if (p == pattern.length()) {
            return w == word.length() ? 1 : 0;
        }

        if (pattern.charAt(p) == '*') {
            if (match(p + 1, w) == 1 ||
                    (w < word.length() && match(p, w + 1) == 1)) {
                return cache[p][w] = 1;
            }
        }

        return cache[p][w] = 0;
    }

}
