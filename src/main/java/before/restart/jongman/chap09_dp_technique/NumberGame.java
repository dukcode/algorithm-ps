package before.restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberGame {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;

	private static int[] board;

	private static int[][] cache;

	private static final int MIN = -987_654_321;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			cache = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(cache[i], MIN);
			}

			board = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				board[i] = Integer.parseInt(st.nextToken());
			}

			int left = 0;
			int right = n - 1;
			bw.write(String.valueOf(play(left, right)));
			bw.newLine();
			reconstruct(left, right);
			System.out.println();
		}

		br.close();
		bw.close();
	}

	private static void reconstruct(int left, int right) {
		if (left > right) {
			return;
		}
		if (right - left >= 1) {
			if (play(left, right) == -play(left + 2, right)) {
				System.out.println("왼쪽 2장 없애기");
				reconstruct(left + 2, right);
				return;
			}

			if (play(left, right) == -play(left, right - 2)) {
				System.out.println("오른쪽 2장 없애기");
				reconstruct(left, right - 2);
				return;
			}
		}

		if (play(left, right) == board[left] - play(left + 1, right)) {
			System.out.println("왼쪽 1장 가져가기");
			reconstruct(left + 1, right);
			return;
		}

		if (play(left, right) == board[right] - play(left, right - 1)) {
			System.out.println("오른쪽 1장 가져가기");
			reconstruct(left, right - 1);
			return;
		}
	}

	private static int play(int left, int right) {
		if (left > right) {
			return 0;
		}

		if (cache[left][right] != MIN) {
			return cache[left][right];
		}

		if (right - left >= 1) {
			cache[left][right] = Math.max(-play(left + 2, right), -play(left, right - 2));
		}
		cache[left][right] = Math.max(cache[left][right], board[left] - play(left + 1, right));
		cache[left][right] = Math.max(cache[left][right], board[right] - play(left, right - 1));

		return cache[left][right];
	}

}
