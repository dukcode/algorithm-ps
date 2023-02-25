package restart.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2630 {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[][] board;

	private static int[] ans = new int[2];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new FileReader("input.txt"));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		board = new int[n][n];
		for (int y = 0; y < n; ++y) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; ++x) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		func(0, 0, n);

		bw.write(String.valueOf(ans[0]));
		bw.newLine();
		bw.write(String.valueOf(ans[1]));

		br.close();
		bw.close();
	}

	private static void func(int r, int c, int n) {

		boolean isSame = true;
		Loop:
		for (int y = r; y < r + n; ++y) {
			for (int x = c; x < c + n; ++x) {
				if (board[y][x] != board[r][c]) {
					isSame = false;
					break Loop;
				}
			}
		}

		if (isSame) {
			ans[board[r][c]]++;
			return;
		}

		int half = n / 2;
		for (int y = r; y < r + n; y += half) {
			for (int x = c; x < c + n; x += half) {
				func(y, x, half);
			}
		}

	}
}

