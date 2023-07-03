package practice.jongman.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boggle {

	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int c;
	private static char[][] board;
	private static int n;

	private static String word;

	private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	private static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());

		while (c-- > 0) {
			board = new char[5][];
			for (int y = 0; y < 5; y++) {
				String line = br.readLine();
				board[y] = new char[5];
				for (int x = 0; x < 5; x++) {
					char ch = line.charAt(x);
					board[y][x] = ch;
				}
			}

			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				word = br.readLine();

				bw.write(word);
				bw.write(' ');
				bw.write(canFind() ? "YES" : "NO");
				bw.newLine();
			}

			br.close();
			bw.close();
		}
	}

	private static boolean canFind() {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				if (canFind(0, y, x)) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean canFind(int idx, int y, int x) {
		if (idx == word.length()) {
			return true;
		}

		if (board[y][x] != word.charAt(idx)) {
			return false;
		}

		for (int dir = 0; dir < 8; ++dir) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];

			if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
				continue;
			}

			if (canFind(idx + 1, ny, nx)) {
				return true;
			}
		}

		return false;
	}

}
