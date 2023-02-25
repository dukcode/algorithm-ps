package restart.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B12100 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[][] boardOriginal;
	private static int[][] board;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		boardOriginal = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				boardOriginal[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < (1 << 10); ++i) {
			board = new int[n][n];
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					board[y][x] = boardOriginal[y][x];
				}
			}

			int state = i;

			for (int j = 0; j < 5; ++j) {
				int dir = state % 4;
				move(dir);
				state /= 4;
			}

			int maxBlock = 0;
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					maxBlock = Math.max(maxBlock, board[y][x]);
				}
			}

			ans = Math.max(ans, maxBlock);
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static void move(int dir) {
		for (int i = 0; i < dir; ++i) {
			rotate();
		}
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				System.out.printf("%2d ", board[y][x]);
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < n; ++i) {
			moveLine(i);
		}
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				System.out.printf("%2d ", board[y][x]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("-----------------------");
		dir = (4 - dir) % 4;
		for (int i = 0; i < dir; ++i) {
			rotate();
		}

	}

	private static void rotate() {
		for (int y = 0; y < n; ++y) {
			for (int x = 0; x < n / 2; ++x) {
				int tmp = board[y][x];
				board[y][x] = board[y][n - 1 - x];
				board[y][n - 1 - x] = tmp;
			}
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n - 1 - y; x++) {
				int tmp = board[y][x];
				board[y][x] = board[n - 1 - x][n - 1 - y];
				board[n - 1 - x][n - 1 - y] = tmp;
			}
		}

	}

	private static void moveLine(int idx) {
		int[] arr = board[idx];

		int lastIdx = n - 1;
		for (int x = n - 2; x >= 0; --x) {
			if (arr[x] == 0) {
				continue;
			}

			if (arr[lastIdx] == 0) {
				arr[lastIdx] = arr[x];
				arr[x] = 0;
				continue;
			}

			if (arr[lastIdx] == arr[x]) {
				arr[lastIdx] *= 2;
				arr[x] = 0;
				lastIdx--;
			} else {
				lastIdx--;
				if (lastIdx != x) {
					arr[lastIdx] = arr[x];
					arr[x] = 0;
				}
			}
		}
	}

}
