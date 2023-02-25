package restart.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B18808 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int w;
	private static int h;
	private static int k;

	private static int[][] board;
	private static int[][][] stickers;

	private static int r;
	private static int c;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		board = new int[h][w];

		stickers = new int[k][][];
		for (int i = 0; i < k; ++i) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			int n = Math.max(r, c);

			int[][] sticker = new int[n][n];
			for (int y = 0; y < r; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < c; x++) {
					sticker[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			stickers[i] = sticker;
		}

		for (int i = 0; i < k; ++i) {
			int[][] sticker = stickers[i];
			for (int dir = 0; dir < 4; ++dir) {
				int[][] rotatedSticker = rotate(sticker, dir);
				if (stick(board, rotatedSticker)) {
					break;
				}
			}
		}

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				ans += board[y][x];
			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean stick(int[][] board, int[][] sticker) {
		Point startPoint = findFirstPoint(sticker);
		int size = sticker.length;

		int y = 0;
		int x = 0;
		boolean possible = false;
		OutLoop:
		for (y = 0; y < h; y++) {
			Loop:
			for (x = 0; x < w; x++) {
				if (board[y][x] == 1) {
					continue;
				}

				for (int dy = -startPoint.y; dy < size - startPoint.y; ++dy) {
					for (int dx = -startPoint.x; dx < size - startPoint.x; ++dx) {
						int by = y + dy;
						int bx = x + dx;
						int sy = startPoint.y + dy;
						int sx = startPoint.x + dx;

						if (sticker[sy][sx] == 0) {
							continue;
						}

						if (by < 0 || by >= h || bx < 0 || bx >= w) {
							continue Loop;
						}

						if (board[by][bx] == 1) {
							continue Loop;
						}

					}
				}

				possible = true;
				break OutLoop;
			}
		}

		if (!possible) {
			return false;
		}

		for (int dy = -startPoint.y; dy < size - startPoint.y; ++dy) {
			for (int dx = -startPoint.x; dx < size - startPoint.x; ++dx) {
				int by = y + dy;
				int bx = x + dx;
				int sy = startPoint.y + dy;
				int sx = startPoint.x + dx;

				if (sticker[sy][sx] == 1) {
					board[by][bx] = sticker[sy][sx];
				}

			}
		}

		return true;
	}

	private static Point findFirstPoint(int[][] sticker) {
		int size = sticker.length;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (sticker[y][x] == 1) {
					return new Point(y, x);
				}
			}
		}

		return null;
	}

	private static int[][] rotate(int[][] sticker, int dir) {
		int size = sticker.length;
		int[][] ret = new int[size][size];
		for (int y = 0; y < size; y++) {
			System.arraycopy(sticker[y], 0, ret[y], 0, size);
		}

		while (dir-- > 0) {
			rotateOnce(ret);
		}

		return ret;
	}

	private static void rotateOnce(int[][] sticker) {
		int size = sticker.length;

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size / 2; x++) {
				int tmp = sticker[y][x];
				sticker[y][x] = sticker[y][size - 1 - x];
				sticker[y][size - 1 - x] = tmp;
			}
		}

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size - y; x++) {
				int tmp = sticker[y][x];
				sticker[y][x] = sticker[size - 1 - x][size - 1 - y];
				sticker[size - 1 - x][size - 1 - y] = tmp;
			}
		}
	}

}
