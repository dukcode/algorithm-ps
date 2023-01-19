package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1780 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static int[][] board;

	private static int[] ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		board = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		ans = new int[3];

		func(0, 0, n);

		bw.write(String.valueOf(ans[0]));
		bw.newLine();
		bw.write(String.valueOf(ans[1]));
		bw.newLine();
		bw.write(String.valueOf(ans[2]));

		br.close();
		bw.close();
	}

	private static void func(int r, int c, int n) {
		boolean isSame = true;
		Loop:
		for (int y = r; y < r + n; y++) {
			for (int x = c; x < c + n; x++) {
				if (board[y][x] != board[r][c]) {
					isSame = false;
					break Loop;
				}
			}
		}

		if (isSame) {
			ans[board[r][c] + 1]++;
			return;
		}

		for (int y = r; y < r + n; y += n / 3) {
			for (int x = c; x < c + n; x += n / 3) {
				func(y, x, n / 3);
			}
		}
	}

}
