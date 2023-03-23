package restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Lis {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] arr;
	private static int[] cache;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			cache = new int[n + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			bw.write(String.valueOf(lis(-1) - 1));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int lis(int idx) {

		if (cache[idx + 1] != 0) {
			return cache[idx + 1];
		}

		cache[idx + 1] = 1;
		for (int i = idx + 1; i < n; i++) {
			if (idx != -1 && arr[idx] >= arr[i]) {
				continue;
			}

			cache[idx + 1] = Math.max(cache[idx + 1], lis(i) + 1);
		}

		return cache[idx + 1];
	}
}
