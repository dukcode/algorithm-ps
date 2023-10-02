package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class AsymTiling2 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] entireCache;
	private static int[] asymCache;

	private static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			entireCache = new int[n + 1];
			asymCache = new int[n + 1];

			bw.write(String.valueOf(countAsym(n)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int countAsym(int width) {
		if (width <= 2) {
			return 0;
		}

		if (asymCache[width] != 0) {
			return asymCache[width];
		}

		asymCache[width] = (asymCache[width] + countAsym(width - 2)) % MOD;
		asymCache[width] = (asymCache[width] + countAsym(width - 4)) % MOD;
		asymCache[width] = (asymCache[width] + countTiling(width - 3)) % MOD;
		asymCache[width] = (asymCache[width] + countTiling(width - 3)) % MOD;

		return asymCache[width];
	}

	private static boolean isEven(int num) {
		return num % 2 == 0;
	}

	private static int countTiling(int width) {
		if (width <= 1) {
			return 1;
		}

		if (entireCache[width] != 0) {
			return entireCache[width];
		}

		return entireCache[width] = (countTiling(width - 1) + countTiling(width - 2)) % MOD;
	}

}
