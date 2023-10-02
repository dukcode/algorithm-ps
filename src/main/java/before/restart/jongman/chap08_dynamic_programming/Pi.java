package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Pi {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] numbers;
	private static int[] cache;

	private static final int INF = 987_654_321;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			input();
			bw.write(String.valueOf(solve(0)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve(int idx) {
		if (idx == n) {
			return 0;
		}

		if (cache[idx] != -1) {
			return cache[idx];
		}

		cache[idx] = INF;
		for (int en = idx + 3; en <= idx + 5; en++) {
			if (en > n) {
				continue;
			}
			cache[idx] = Math.min(cache[idx], solve(en) + score(idx, en));
		}

		return cache[idx];
	}

	private static int score(int st, int en) {

		boolean progressive = true;
		for (int i = st; i < en - 2; i++) {
			if (numbers[i + 1] - numbers[i] != numbers[i + 2] - numbers[i + 1]) {
				progressive = false;
				break;
			}
		}

		if (progressive) {
			int diff = numbers[st + 1] - numbers[st];

			if (diff == 0) {
				return 1;
			} else if (diff == 1 || diff == -1) {
				return 2;
			} else {
				return 5;
			}
		}

		boolean alternative = true;
		for (int i = st; i < en - 2; i++) {
			if (numbers[i] != numbers[i + 2]) {
				alternative = false;
				break;
			}
		}

		if (alternative) {
			return 4;
		}

		return 10;
	}

	private static void input() throws IOException {
		String line = br.readLine();
		n = line.length();
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = line.charAt(i) - '0';
		}

		cache = new int[n];
		Arrays.fill(cache, -1);
	}

}
