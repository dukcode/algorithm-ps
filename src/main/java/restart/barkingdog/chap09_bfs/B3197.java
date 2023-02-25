package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3197 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

	private static int[][] board;
	private static boolean[][] vis;
	private static boolean[][] lVis;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	private static Queue<Point> q = new LinkedList<>();
	private static Queue<Point> lq = new LinkedList<>();

	private static List<Point> swans = new ArrayList<>();
	private static Point start;
	private static Point end;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				char block = line.charAt(x);

				if (block == 'X') {
					board[y][x] = 1;
				} else if (block == 'L') {
					swans.add(new Point(y, x));
					q.offer(new Point(y, x));
				} else {
					q.offer(new Point(y, x));
				}
			}
		}

		vis = new boolean[h][w];
		lVis = new boolean[h][w];

		start = swans.get(0);
		end = swans.get(1);

		lq.offer(start);
		lVis[start.y][start.x] = true;

		while (!bfs()) {
			melt();
			ans++;
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();

	}

	private static boolean bfs() {

		Queue<Point> nq = new LinkedList<>();

		while (!lq.isEmpty()) {
			Point cur = lq.poll();

			if (cur.y == end.y && cur.x == end.x) {
				return true;
			}

			for (int i = 0; i < 4; ++i) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (outOfBound(ny, nx)) {
					continue;
				}

				if (lVis[ny][nx]) {
					continue;
				}

				lVis[ny][nx] = true;
				if (board[ny][nx] == 1) {
					nq.offer(new Point(ny, nx));
				} else {
					lq.offer(new Point(ny, nx));
				}
			}
		}

		lq.addAll(nq);

		return false;
	}

	private static void melt() {
		Queue<Point> nq = new LinkedList<>();
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];

				if (outOfBound(ny, nx)) {
					continue;
				}

				if (vis[ny][nx]) {
					continue;
				}

				vis[ny][nx] = true;

				if (board[ny][nx] == 1) {
					nq.offer(new Point(ny, nx));
				} else {
					q.offer(new Point(ny, nx));
				}
			}
		}

		for (Point p : nq) {
			board[p.y][p.x] = 0;
			q.offer(p);
		}
	}

	private static boolean outOfBound(int y, int x) {
		return y < 0 || y >= h || x < 0 || x >= w;
	}
}
