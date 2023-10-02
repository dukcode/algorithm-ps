package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglePath {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;

	private static int[][] board;
	private static int[][] cache;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			board = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x <= y; x++) {
					board[y][x] = Integer.parseInt(st.nextToken());
				}
				System.out.println(Arrays.toString(board[y]));
			}

			cache = new int[n][n];

			bw.write(String.valueOf(solve(0, 0)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve(int y, int x) {
		if (cache[y][x] != 0) {
			return cache[y][x];
		}

		if (y == n - 1) {
			return board[y][x];
		}

		return cache[y][x] = board[y][x] + Math.max(solve(y + 1, x), solve(y + 1, x + 1));
	}

}
