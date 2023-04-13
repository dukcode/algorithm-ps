package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Zimbabwe {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static char[] e;
	private static int m;

	private static char[] digits;
	private static int[][][] cache;    // mod, less, taken
	private static int n;

	private static final int MOD = 1_000_000_007;

	private static final int TRUE = 1;
	private static final int FALSE = 0;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			e = st.nextToken().toCharArray();
			m = Integer.parseInt(st.nextToken());

			digits = new char[e.length];
			System.arraycopy(e, 0, digits, 0, e.length);
			Arrays.sort(digits);

			n = e.length;

			cache = new int[m][2][1 << n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < 2; j++) {
					Arrays.fill(cache[i][j], -1);
				}
			}

			bw.write(String.valueOf(price(0, 0, 0, TRUE)));
		}

		br.close();
		bw.close();
	}

	private static int price(int idx, int taken, int mod, int less) {
		if (idx == n) {
			return (less == 1 && mod == 0) ? 1 : 0;
		}

		if (cache[mod][less][taken] != -1) {
			return cache[mod][less][taken];
		}

		for (int next = 0; next < n; next++) {
			if ((taken & (1 << next)) == TRUE) {
				continue;
			}

			if (less == FALSE && e[idx] < digits[next]) {
				continue;
			}

			if (next > 0 && digits[next - 1] == digits[next] &&
				((taken & (1 << (next - 1))) == 0)) {
				continue;
			}

			int nextTaken = taken | (1 << next);
			int nextMod = (mod * 10 + (digits[next] - '0')) % m;
			int nextLess = less == TRUE || e[idx] > digits[next] ? TRUE : FALSE;

			cache[mod][less][taken] += price(idx + 1, nextTaken, nextMod, nextLess);
			cache[mod][less][taken] %= MOD;
		}

		return cache[mod][less][taken];
	}

}
