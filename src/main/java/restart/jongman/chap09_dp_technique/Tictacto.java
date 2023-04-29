package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tictacto {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static char[][] board;

	private static int c;

	private static int[] cache = new int[(int)Math.pow(3, 9)];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());

		Arrays.fill(cache, -2);
		while (c-- > 0) {
			board = new char[3][3];
			for (int i = 0; i < 3; ++i) {
				board[i] = br.readLine().toCharArray();
			}

			char turn = calculateTurn(board);
			int ret = canWin(board, turn);

			if (ret == 0) {
				bw.write("TIE");
			} else {
				bw.write(ret == 1 ? turn : (char)('x' + 'o' - turn));
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static char calculateTurn(char[][] board) {
		int cntX = 0;
		int cntO = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (board[y][x] == 'x') {
					cntX++;
				} else if (board[y][x] == 'o') {
					cntO++;
				}
			}
		}

		return cntX <= cntO ? 'x' : 'o';
	}

	private static int bijection(char[][] board) {
		int ret = 0;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				ret *= 3;
				if (board[y][x] == 'o') {
					ret++;
				} else if (board[y][x] == 'x') {
					ret += 2;
				}
			}
		}

		return ret;
	}

	private static int canWin(char[][] board, char turn) {
		if (isFinished(board, (char)('o' + 'x' - turn))) {
			return -1;
		}

		int seq = bijection(board);
		if (cache[seq] != -2) {
			return cache[seq];
		}

		int minValue = 2;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (board[y][x] == '.') {
					board[y][x] = turn;
					minValue = Math.min(minValue, canWin(board, (char)('o' + 'x' - turn)));
					board[y][x] = '.';
				}
			}
		}

		if (minValue == 2 || minValue == 0) {
			return cache[seq] = 0;
		}

		return cache[seq] = -minValue;
	}

	private static boolean isFinished(char[][] board, char turn) {
		for (int y = 0; y < 3; y++) {
			boolean isHorizonFinished = true;
			boolean isVerticalFinished = true;

			for (int x = 0; x < 3; x++) {
				if (board[y][x] != turn) {
					isHorizonFinished = false;
				}
				if (board[x][y] != turn) {
					isVerticalFinished = false;
				}
			}

			if (isHorizonFinished || isVerticalFinished) {
				return true;
			}
		}

		boolean isRightDownFinished = true;
		boolean isLeftDownFinished = true;
		for (int i = 0; i < 3; ++i) {
			if (board[i][i] != turn) {
				isRightDownFinished = false;
			}
			if (board[i][2 - i] != turn) {
				isLeftDownFinished = false;
			}
		}

		if (isRightDownFinished || isLeftDownFinished) {
			return true;
		}

		return false;
	}

}
