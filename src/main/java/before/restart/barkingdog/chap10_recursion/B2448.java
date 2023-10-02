package before.restart.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B2448 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;

	private static int h;
	private static int w;
	private static char[][] board;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		h = n;
		w = 2 * (n - 1) + 1;

		board = new char[h][w];
		for (int y = 0; y < h; y++) {
			Arrays.fill(board[y], ' ');
		}

		func(0, n - 1, n);

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				bw.write(board[y][x]);
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void func(int r, int c, int size) {
		if (size == 3) {
			board[r][c] = '*';
			board[r + 1][c - 1] = '*';
			board[r + 1][c + 1] = '*';
			for (int x = c - 2; x <= c + 2; ++x) {
				board[r + 2][x] = '*';
			}

			return;
		}

		int half = size / 2;
		func(r, c, half);
		func(r + half, c - half, half);
		func(r + half, c + half, half);
	}

}
