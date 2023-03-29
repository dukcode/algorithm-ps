package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Morse {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;

	private static int n;
	private static int m;
	private static int k;

	private static int[][] bino = new int[201][201];
	private static final int MX = 1_000_000_000 + 100;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		calcBino();

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			generate3(n, m, "");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void calcBino() {
		for (int y = 0; y <= 200; y++) {
			bino[y][0] = bino[y][y] = 1;
			for (int x = 1; x < y; x++) {
				bino[y][x] = Math.min(MX, bino[y - 1][x - 1] + bino[y - 1][x]);
			}
		}
	}

	private static void generate(int n, int m, String s) throws IOException {
		if (n == 0 && m == 0) {
			bw.write(s);
		}

		if (n > 0) {
			generate(n - 1, m, s + "-");
		}

		if (m > 0) {
			generate(n, m - 1, s + "o");
		}
	}

	private static void generate2(int n, int m, String s) throws IOException {
		if (k < 0) {
			return;
		}

		if (n == 0 && m == 0) {
			--k;
			if (k == 0) {
				bw.write(s);
			}

			return;
		}

		if (n > 0) {
			generate2(n - 1, m, s + "-");
		}

		if (m > 0) {
			generate2(n, m - 1, s + "o");
		}
	}

	private static void generate3(int n, int m, String s) throws IOException {
		if (k < 0) {
			return;
		}

		if (n == 0 && m == 0) {
			--k;
			if (k == 0) {
				bw.write(s);
			}

			return;
		}

		if (bino[n + m][n] < k) {
			k -= bino[n + m][n];
			return;
		}

		if (n > 0) {
			generate2(n - 1, m, s + "-");
		}

		if (m > 0) {
			generate2(n, m - 1, s + "o");
		}
	}

	private String generate4(int n, int m, int skip) {
		if (n == 0) {
			return "o".repeat(m);
		}

		if (skip < bino[n + m - 1][n - 1]) {
			return "-" + generate4(n - 1, m, skip);
		}

		return "o" + generate4(n, m - 1, skip - bino[n + m - 1][n - 1]);
	}
}
