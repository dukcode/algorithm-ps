package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Poly {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;

	private static int[][] cache = new int[100 + 1][100 + 1];

	private static final int MOD = 10_000_000;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			int ans = 0;
			for (int numTop = 1; numTop <= n; numTop++) {
				ans = (ans + count(n, numTop)) % MOD;
			}

			bw.write(String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int count(int numBlock, int numTop) {
		if (numBlock == numTop) {
			return 1;
		}

		if (cache[numBlock][numTop] != 0) {
			return cache[numBlock][numTop];
		}

		for (int nextTopSize = 1; nextTopSize <= numBlock - numTop; nextTopSize++) {
			cache[numBlock][numTop] =
				(cache[numBlock][numTop] + count(numBlock - numTop, nextTopSize) * (nextTopSize + numTop - 1) % MOD)
					% MOD;
		}

		return cache[numBlock][numTop];
	}

}
