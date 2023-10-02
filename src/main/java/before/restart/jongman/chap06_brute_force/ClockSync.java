package before.restart.jongman.chap06_brute_force;

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

	private static final int INF = 987_654_321;
	private static final int NUM_CLOCKS = 16;
	private static final int NUM_SWITCHES = 10;

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

	private static int[] clocks;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			clocks = new int[16];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 16; i++) {
				clocks[i] = Integer.parseInt(st.nextToken());
			}

			int ans = countMin(0);
			bw.write(String.valueOf(ans == INF ? -1 : ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int countMin(int switchIdx) {
		if (switchIdx == NUM_SWITCHES) {
			return isAligned() ? 0 : INF;
		}

		int ret = INF;
		for (int count = 0; count < 4; count++) {
			ret = Math.min(ret, countMin(switchIdx + 1) + count);
			push(switchIdx);
		}

		return ret;
	}

	private static boolean isAligned() {
		for (int i = 0; i < NUM_CLOCKS; i++) {
			if (clocks[i] != 12) {
				return false;
			}
		}
		return true;
	}

	private static void push(int switchIdx) {
		int[] toMove = switches[switchIdx];

		for (int clockIdx : toMove) {
			clocks[clockIdx] += 3;
			if (clocks[clockIdx] == 15) {
				clocks[clockIdx] = 3;
			}
		}
	}

}
