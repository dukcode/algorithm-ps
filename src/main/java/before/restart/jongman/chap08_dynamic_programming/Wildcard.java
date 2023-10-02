package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Wildcard {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

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

	private static int match(int patternIdx, int wordIdx) {
		int originPatternIdx = patternIdx;
		int originWordIdx = wordIdx;

		if (cache[originPatternIdx][originWordIdx] != -1) {
			return cache[originPatternIdx][originWordIdx];
		}

		while (patternIdx < pattern.length() && wordIdx < word.length()
			&& (pattern.charAt(patternIdx) == '?' || pattern.charAt(patternIdx) == word.charAt(wordIdx))) {
			patternIdx++;
			wordIdx++;
		}

		if (patternIdx == pattern.length()) {
			return wordIdx == word.length() ? 1 : 0;
		}

		if (pattern.charAt(patternIdx) == '*') {
			for (int skip = 0; wordIdx + skip <= word.length(); skip++) {
				if (match(patternIdx + 1, wordIdx + skip) == 1) {
					return cache[originPatternIdx][originWordIdx] = 1;
				}
			}
		}

		return cache[originPatternIdx][originWordIdx] = 0;
	}

}
