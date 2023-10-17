package jongman.chap08_dynamic_programming;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class WildCard {
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final int NONE = -1;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int c;
    private static String pattern;
    private static String word;
    private static int n;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            pattern = br.readLine();
            n = Integer.parseInt(br.readLine());

            Queue<String> ans = new PriorityQueue<>();
            for (int i = 0; i < n; ++i) {
                word = br.readLine();

                cache = new int[pattern.length()][word.length()];
                for (int y = 0; y < pattern.length(); ++y) {
                    Arrays.fill(cache[y], NONE);
                }


                if (match(0, 0) == TRUE) {
                    ans.add(word);
                }
            }

            for (String matched : ans) {
                bw.write(matched);
                bw.newLine();
            }

        }

        br.close();
        bw.close();
    }

    private static int match(int p, int w) {
        if (p == pattern.length()) {
            return w == word.length() ? TRUE : FALSE;
        }

        if (w == word.length()) {
            if (pattern.charAt(p) == '*') {
                return match(p + 1, w);
            } else {
                return FALSE;
            }
        }

        if (cache[p][w] != -1) {
            return cache[p][w];
        }

        char pc = pattern.charAt(p);
        char wc = word.charAt(w);

        if (pc == '*') {
            return cache[p][w] = (match(p, w + 1) | match(p + 1, w + 1));
        } else if (pc == '?' || pc == wc) {
            return cache[p][w] = match(p + 1, w + 1);
        }

        return cache[p][w] = FALSE;
    }

}
