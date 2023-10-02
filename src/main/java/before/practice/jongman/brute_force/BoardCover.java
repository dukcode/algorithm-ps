package before.practice.jongman.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BoardCover {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int h;
	private static int w;
	private static int[][] board;

	private static int[][][] blocks = {
		{{0, 0}, {1, 0}, {1, 1}},    // l_
		{{0, 0}, {1, 0}, {0, 1}},    // l^
		{{0, 0}, {0, 1}, {1, 1}},    // ^l
		{{0, 0}, {1, 0}, {1, -1}}    // _l
	};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			board = new int[h][w];
			int countBlank = 0;
			for (int y = 0; y < h; y++) {
				String line = br.readLine();
				for (int x = 0; x < w; x++) {
					char b = line.charAt(x);
					if (b == '#') {
						board[y][x] = 1;
					} else {
						countBlank++;
					}
				}
			}

			if (countBlank % 3 != 0) {
				bw.write('0');
			} else {
				bw.write(String.valueOf(count()));
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int count() {

		int sy = -1;
		int sx = -1;

		Loop:
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (board[y][x] == 0) {
					sy = y;
					sx = x;
					break Loop;
				}
			}
		}

		if (sy == -1) {
			return 1;
		}

		int ret = 0;
		for (int dir = 0; dir < 4; ++dir) {
			if (draw(sy, sx, dir, 1)) {
				ret += count();
			}

			draw(sy, sx, dir, -1);
		}

		return ret;
	}

	private static boolean draw(int y, int x, int dir, int value) {
		int[][] block = blocks[dir];

		boolean ret = true;
		for (int i = 0; i < 3; ++i) {
			int ny = y + block[i][0];
			int nx = x + block[i][1];

			if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
				ret = false;
				continue;
			}

			if (board[ny][nx] == 1) {
				ret = false;
			}

			board[ny][nx] += value;
		}

		return ret;
	}

}
