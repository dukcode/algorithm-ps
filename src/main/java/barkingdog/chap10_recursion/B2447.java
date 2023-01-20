package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2447 {
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
		func(0, 0, n);

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				bw.write(board[y][x] == 1 ? '*' : ' ');
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void func(int r, int c, int size) {
		if (size == 3) {
			for (int y = r; y < r + size; y++) {
				for (int x = c; x < c + size; x++) {
					if (y == r + 1 && x == c + 1) {
						continue;
					}
					board[y][x] = 1;
				}
			}

			return;
		}

		int third = size / 3;
		for (int y = r; y < r + size; y += third) {
			for (int x = c; x < c + size; x += third) {
				if (y == r + third && x == c + third) {
					continue;
				}

				func(y, x, third);
			}
		}

	}

}
