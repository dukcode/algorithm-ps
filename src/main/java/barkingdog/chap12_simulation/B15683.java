package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15683 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int[][] board;
	private static boolean[][] visOriginal;

	private static int numCctv;
	private static List<Point> cctvs = new ArrayList<>();
	private static List<Point> cctv5s = new ArrayList<>();

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {0, -1, 0, 1};
	private static int[] dx = {1, 0, -1, 0};

	private static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		input();

		visOriginal = new boolean[h][w];
		for (Point cctv5 : cctv5s) {
			record(visOriginal, cctv5, 0);
		}

		for (int i = 0; i < (1 << numCctv * 2); ++i) {
			int state = i;
			boolean[][] vis = copyVis(visOriginal);
			for (int idx = 0; idx < numCctv; ++idx) {
				int dir = state % 4;
				record(vis, cctvs.get(idx), dir);
				state /= 4;
			}

			ans = Math.min(ans, countBlindSpot(vis));
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static int countBlindSpot(boolean[][] vis) {
		int ret = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (!vis[y][x] && board[y][x] == 0) {
					ret++;
				}
			}
		}
		return ret;
	}

	private static boolean[][] copyVis(boolean[][] visOriginal) {
		boolean[][] ret = new boolean[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				ret[y][x] = visOriginal[y][x];
			}
		}
		return ret;
	}

	private static void record(boolean[][] vis, Point pos, int dir) {
		int cctv = board[pos.y][pos.x];

		switch (cctv) {
			case 1:
				recordLine(vis, pos, dir);
				break;
			case 2:
				recordLine(vis, pos, dir);
				recordLine(vis, pos, (dir + 2) % 4);
				break;
			case 3:
				recordLine(vis, pos, dir);
				recordLine(vis, pos, (dir + 1) % 4);
				break;
			case 4:
				recordLine(vis, pos, dir);
				recordLine(vis, pos, (dir + 1) % 4);
				recordLine(vis, pos, (dir + 2) % 4);
				break;
			case 5:
				recordLine(vis, pos, dir);
				recordLine(vis, pos, (dir + 1) % 4);
				recordLine(vis, pos, (dir + 2) % 4);
				recordLine(vis, pos, (dir + 3) % 4);
				break;
			default:
				break;
		}
	}

	private static void recordLine(boolean[][] vis, Point cctv, int dir) {
		int y = cctv.y;
		int x = cctv.x;
		while (true) {
			y += dy[dir];
			x += dx[dir];

			if (y < 0 || y >= h || x < 0 || x >= w) {
				return;
			}

			if (board[y][x] == 6) {
				return;
			}

			vis[y][x] = true;
		}
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				int cctv = Integer.parseInt(st.nextToken());
				if (1 <= cctv && cctv <= 4) {
					cctvs.add(new Point(y, x));
				} else if (cctv == 5) {
					cctv5s.add(new Point(y, x));
				}
				board[y][x] = cctv;
			}
		}

		numCctv = cctvs.size();
	}

}
