package before.restart.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1799 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[][] board;
	private static boolean[] taken;

	private static int cnt;
	private static int ans;

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

		taken = new boolean[2 * n - 1];

		cnt = 0;
		func(1 - n, 0);
		ans += cnt;
		cnt = 0;
		func(2 - n, 0);
		ans += cnt;

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static void func(int sub, int num) {
		if (sub > n - 1) {
			cnt = Math.max(cnt, num);
			return;
		}

		int stX = sub <= 0 ? 0 : sub;
		int enX = sub <= 0 ? sub + n : n;

		for (int x = stX; x < enX; ++x) {
			int y = x - sub;

			if (board[y][x] == 0 || taken[x + y]) {
				continue;
			}

			taken[x + y] = true;
			func(sub + 2, num + 1);
			taken[x + y] = false;
		}

		func(sub + 2, num);

	}

}
