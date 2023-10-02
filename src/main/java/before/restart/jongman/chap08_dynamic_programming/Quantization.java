package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantization {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int s;
	private static int[] arr;
	private static int[] partSum;
	private static int[] partSquareSum;

	private static int[][] cache;

	private static final int INF = 987_654_321;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			input();
			bw.write(String.valueOf(quantize(s - 1, 0)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int quantize(int parts, int idx) {
		if (idx == n) {
			return 0;
		}

		if (parts == -1) {
			return INF;
		}

		if (cache[parts][idx] != -1) {
			return cache[parts][idx];
		}

		cache[parts][idx] = INF;
		for (int size = 1; idx + size <= n; size++) {
			cache[parts][idx] = Math.min(cache[parts][idx],
				minErrs(idx, idx + size) + quantize(parts - 1, idx + size));
		}

		return cache[parts][idx];
	}

	private static int minErrs(int from, int to) {
		int pSum = partSum[to] - partSum[from];
		int pSqSum = partSquareSum[to] - partSquareSum[from];
		int mean = (int)(0.5 + (double)pSum / (to - from));

		return pSqSum - 2 * mean * pSum + (to - from) * mean * mean;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		Arrays.sort(arr);

		partSum = new int[n + 1];
		partSquareSum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			partSum[i + 1] = partSum[i] + arr[i];
			partSquareSum[i + 1] = partSquareSum[i] + arr[i] * arr[i];
		}

		cache = new int[s][n];
		for (int i = 0; i < s; i++) {
			Arrays.fill(cache[i], -1);
		}
	}

}
