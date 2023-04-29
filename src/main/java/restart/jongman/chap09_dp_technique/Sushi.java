package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sushi {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int m;
	private static int[] costs;
	private static int[] preferences;

	private static final int MAX_BUDGET = 100_000_000;

	private static int[] cache;
	private static int[] cache2;
	private static int[] cache3;    // 201개로 초기화

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			costs = new int[n];
			preferences = new int[n];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				costs[i] = Integer.parseInt(st.nextToken());
				preferences[i] = Integer.parseInt(st.nextToken());
			}

			cache = new int[m + 1];
			Arrays.fill(cache, -1);

			bw.write(String.valueOf(sushi(m)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	// m과 costs는 이미 100으로 나누어져 있음
	private static int sushi3() {
		int ret = 0;

		cache3[0] = 0;
		for (int budget = 1; budget <= m; ++budget) {
			int cand = 0;
			for (int dish = 0; dish < n; ++dish) {
				if (budget >= costs[dish]) {
					cand = Math.max(cache3[budget % 201], cache2[(budget - costs[dish]) % 201] + preferences[dish]);
				}
			}
			cache3[budget % 201] = cand;

			ret = Math.max(ret, cand);
		}

		return ret;
	}

	private static int sushi2() {
		int ret = 0;
		for (int budget = 1; budget <= m; ++budget) {
			for (int dish = 0; dish < n; ++dish) {
				cache2[budget] = 0;
				if (budget >= costs[dish]) {
					cache2[budget] = Math.max(cache2[budget], cache2[budget - costs[dish]] + preferences[dish]);
				}
			}

			ret = Math.max(ret, cache2[budget]);
		}

		return ret;
	}

	private static int sushi(int budget) {
		if (cache[budget] != -1) {
			return cache[budget];
		}

		cache[budget] = 0;
		for (int i = 0; i < n; ++i) {
			if (budget < costs[i]) {
				continue;
			}

			cache[budget] = Math.max(cache[budget], sushi(budget - costs[i]) + preferences[i]);
		}

		return cache[budget];
	}

}
