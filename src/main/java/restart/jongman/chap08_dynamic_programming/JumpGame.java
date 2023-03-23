package restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpGame {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[][] board;
	private static int[][] cache;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			cache = new int[n][n];
			for (int y = 0; y < n; y++) {
				Arrays.fill(cache[y], -1);
			}
			board = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					int num = Integer.parseInt(st.nextToken());
					board[y][x] = num;
				}
			}

			bw.write(solve(0, 0) == 1 ? "YES" : "NO");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve(int y, int x) {
		if (y >= n || x >= n) {
			return 0;
		}

		if (y == n - 1 && x == n - 1) {
			return 1;
		}

		if (cache[y][x] != -1) {
			return cache[y][x];
		}

		int d = board[y][x];

		return cache[y][x] = solve(y + d, x) | solve(y, x + d);
	}

}
