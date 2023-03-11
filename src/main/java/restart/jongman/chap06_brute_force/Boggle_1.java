package restart.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boggle_1 {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static char[][] board;

	// 3시 부터 반시계 방향
	private static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	private static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			board = new char[5][5];
			for (int y = 0; y < 5; y++) {
				String line = br.readLine();
				for (int x = 0; x < 5; x++) {
					board[y][x] = line.charAt(x);
				}
			}

			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String word = br.readLine();

				bw.write(word);
				bw.write(' ');
				boolean canFind = false;
				Loop:
				for (int y = 0; y < 5; y++) {
					for (int x = 0; x < 5; x++) {
						if (find(word, 0, y, x)) {
							canFind = true;
							break Loop;
						}
					}
				}
				bw.write(canFind ? "YES" : "NO");
				bw.newLine();
			}
		}

		br.close();
		bw.close();
	}

	private static boolean find(String word, int idx, int y, int x) {
		if (y < 0 || y >= 5 || x < 0 || x >= 5) {
			return false;
		}

		if (board[y][x] != word.charAt(idx)) {
			return false;
		}

		if (idx == word.length() - 1) {
			return true;
		}

		for (int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (find(word, idx + 1, ny, nx)) {
				return true;
			}
		}

		return false;
	}

}
