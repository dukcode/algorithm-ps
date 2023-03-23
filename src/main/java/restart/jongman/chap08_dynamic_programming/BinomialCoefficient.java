package restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BinomialCoefficient {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int r;

	private static final int MX = 30;
	private static long[][] cache = new long[MX][MX];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		bw.write(String.valueOf(bino(n, r)));

		br.close();
		bw.close();
	}

	// nCr == (n-1)Cr + (n-1)C(r-1)
	private static long bino(int n, int r) {
		System.out.println(n + "C" + r);
		if (n == r || r == 0) {
			return 1;
		}

		if (cache[n][r] != 0) {
			return cache[n][r];
		}

		return cache[n][r] = bino(n - 1, r) + bino(n - 1, r - 1);
	}

}
