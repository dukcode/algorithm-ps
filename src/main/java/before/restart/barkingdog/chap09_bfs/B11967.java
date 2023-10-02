package before.restart.barkingdog.chap09_bfs;

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

public class B11967 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;

	private static int[][] board;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static List<Point>[][] switches;
	private static boolean[][] vis;

	private static int[] dy = {0, 0, -1, 1};
	private static int[] dx = {-1, 1, 0, 0};

	private static int cnt = 0;

	private static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		vis = new boolean[n][n];

		switches = new ArrayList[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				switches[y][x] = new ArrayList<>();
			}
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			switches[y][x].add(new Point(a, b));
		}

		board[0][0] = 1;
		q.offer(new Point(0, 0));
		vis[0][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (Point p : switches[cur.y][cur.x]) {
				if (vis[p.y][p.x]) {
					continue;
				}

				board[p.y][p.x] = 1;
				if (reachable(p)) {
					vis[p.y][p.x] = true;
					q.offer(new Point(p.y, p.x));
				}
			}

			for (int i = 0; i < 4; ++i) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
					continue;
				}

				if (board[ny][nx] == 0 || vis[ny][nx]) {
					continue;
				}

				vis[ny][nx] = true;
				q.offer(new Point(ny, nx));
			}
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				cnt += board[y][x];
			}
		}

		bw.write(String.valueOf(cnt));

		br.close();
		bw.close();
	}

	private static boolean reachable(Point p) {
		for (int i = 0; i < 4; ++i) {
			int ny = p.y + dy[i];
			int nx = p.x + dx[i];

			if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
				continue;
			}

			if (vis[ny][nx]) {
				return true;
			}
		}

		return false;
	}

}
