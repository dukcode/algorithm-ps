package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Jlis {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int an;
	private static int bn;
	private static int[] aArr;
	private static int[] bArr;
	private static long[][] cache;

	private static final long NEG_INF = Long.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			input();
			bw.write(String.valueOf(solve(-1, -1) - 2L));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static long solve(int aIdx, int bIdx) {
		if (cache[aIdx + 1][bIdx + 1] != 0) {
			return cache[aIdx + 1][bIdx + 1];
		}

		long a = aIdx == -1 ? NEG_INF : aArr[aIdx];
		long b = bIdx == -1 ? NEG_INF : bArr[bIdx];
		long maxElement = Math.max(a, b);

		cache[aIdx + 1][bIdx + 1] = 2;
		for (int nextAIdx = aIdx + 1; nextAIdx < an; nextAIdx++) {
			if (aArr[nextAIdx] > maxElement) {
				cache[aIdx + 1][bIdx + 1] = Math.max(cache[aIdx + 1][bIdx + 1], solve(nextAIdx, bIdx) + 1L);
			}
		}
		for (int nextBIdx = bIdx + 1; nextBIdx < bn; nextBIdx++) {
			if (bArr[nextBIdx] > maxElement) {
				cache[aIdx + 1][bIdx + 1] = Math.max(cache[aIdx + 1][bIdx + 1], solve(aIdx, nextBIdx) + 1L);
			}
		}

		return cache[aIdx + 1][bIdx + 1];
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		an = Integer.parseInt(st.nextToken());
		bn = Integer.parseInt(st.nextToken());
		aArr = new int[an];
		bArr = new int[bn];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < an; i++) {
			aArr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < bn; i++) {
			bArr[i] = Integer.parseInt(st.nextToken());
		}

		cache = new long[an + 1][bn + 1];
	}

}
