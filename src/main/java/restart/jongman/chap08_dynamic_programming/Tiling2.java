package restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Tiling2 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] cache = new int[101];

	private static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			bw.write(String.valueOf(count(n)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int count(int idx) {
		if (idx == 1 || idx == 2) {
			return idx;
		}

		if (cache[idx] != 0) {
			return cache[idx];
		}

		return cache[idx] = (count(idx - 1) + count(idx - 2)) % MOD;
	}

}
