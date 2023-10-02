package before.practice.jongman.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ClockSync {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int[] clocks;

	private static final int[][] switches = {
		{0, 1, 2},
		{3, 7, 9, 11},
		{4, 10, 14, 15},
		{0, 4, 5, 6, 7},
		{6, 7, 8, 10, 12},
		{0, 2, 14, 15},
		{3, 14, 15},
		{4, 5, 7, 14, 15},
		{1, 2, 3, 4, 5},
		{3, 4, 5, 9, 13}
	};

	private static final int INF = 987_654_321;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			clocks = new int[16];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 16; ++i) {
				clocks[i] = Integer.parseInt(st.nextToken());
			}

			int ans = solve(0, 0);
			bw.write(String.valueOf(ans == INF ? -1 : ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve(int idx, int count) {
		if (idx == 10) {
			return isAligned() ? count : INF;
		}

		int ret = INF;
		for (int pushCount = 0; pushCount < 4; ++pushCount) {
			ret = Math.min(ret, solve(idx + 1, count + pushCount));
			push(idx);
		}

		return ret;
	}

	private static void push(int idx) {
		int[] s = switches[idx];
		for (int clockIdx : s) {
			clocks[clockIdx] += 3;
			if (clocks[clockIdx] > 12) {
				clocks[clockIdx] -= 12;
			}
		}
	}

	private static boolean isAligned() {
		for (int i = 0; i < 16; i++) {
			if (clocks[i] != 12) {
				return false;
			}
		}

		return true;
	}
}
