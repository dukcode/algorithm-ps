package before.restart.jongman.chap06_brute_force;

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

	private static int[][][] coverType = {
		{{0, 0}, {1, 0}, {1, 1}},
		{{0, 0}, {1, 0}, {1, -1}},
		{{0, 0}, {0, 1}, {1, 1}},
		{{0, 0}, {1, 0}, {0, 1}}
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
			for (int y = 0; y < h; y++) {
				String line = br.readLine();
				for (int x = 0; x < w; x++) {
					char block = line.charAt(x);
					board[y][x] = block == '#' ? 1 : 0;
				}
			}

			bw.write(String.valueOf(cover()));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int cover() {
		int firstY = -1;
		int firstX = -1;
		Loop:
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (board[y][x] == 0) {
					firstY = y;
					firstX = x;
					break Loop;
				}
			}
		}

		if (firstY == -1) {
			return 1;
		}

		int ret = 0;
		for (int type = 0; type < 4; type++) {
			if (set(firstY, firstX, type, 1)) {
				ret += cover();
			}
			set(firstY, firstX, type, -1);
		}

		return ret;
	}

	private static boolean set(int y, int x, int type, int delta) {
		boolean ret = true;
		for (int i = 0; i < 3; i++) {
			int ny = y + coverType[type][i][0];
			int nx = x + coverType[type][i][1];

			if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
				ret = false;
				continue;
			}

			board[ny][nx] += delta;

			if (board[ny][nx] > 1) {
				ret = false;
			}
		}

		return ret;
	}

}
