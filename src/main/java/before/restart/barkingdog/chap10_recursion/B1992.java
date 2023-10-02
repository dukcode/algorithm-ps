package before.restart.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1992 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		for (int y = 0; y < n; y++) {
			String line = br.readLine();
			for (int x = 0; x < n; x++) {
				board[y][x] = line.charAt(x) - '0';
			}
		}

		bw.write(func(n, 0, 0));

		br.close();
		bw.close();
	}

	private static String func(int size, int r, int c) {
		boolean isSame = true;
		Loop:
		for (int y = r; y < r + size; y++) {
			for (int x = c; x < c + size; x++) {
				if (board[y][x] != board[r][c]) {
					isSame = false;
					break Loop;
				}
			}
		}

		if (isSame) {
			return String.valueOf(board[r][c]);
		}

		int half = size / 2;
		return "(" + func(half, r, c)
			+ func(half, r, c + half)
			+ func(half, r + half, c)
			+ func(half, r + half, c + half) + ")";
	}

}
