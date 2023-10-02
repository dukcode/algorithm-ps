package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[][] board;
	private static boolean[][] vis;

	private static int[][] dist;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {0, 0, -1, 1};
	private static int[] dx = {-1, 1, 0, 0};

	private static Queue<Point> q1;
	private static Queue<Point> q2 = new LinkedList<>();

	private static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		board = new int[n][n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if (board[y][x] != 0) {
					q2.offer(new Point(y, x));
				}
			}
		}

		vis = new boolean[n][n];

		int idx = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (board[y][x] == 0 || vis[y][x]) {
					continue;
				}

				idx++;
				q1 = new LinkedList<>();

				q1.offer(new Point(y, x));
				vis[y][x] = true;
				board[y][x] = idx;

				while (!q1.isEmpty()) {
					Point cur = q1.poll();

					for (int i = 0; i < 4; i++) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
							continue;
						}

						if (vis[ny][nx] || board[ny][nx] == 0) {
							continue;
						}

						board[ny][nx] = idx;
						vis[ny][nx] = true;
						q1.offer(new Point(ny, nx));
					}
				}
			}
		}

		dist = new int[n][n];
		while (!q2.isEmpty()) {
			Point cur = q2.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
					continue;
				}

				if (board[ny][nx] == board[cur.y][cur.x]) {
					continue;
				}

				if (board[ny][nx] == 0) {
					board[ny][nx] = board[cur.y][cur.x];
					dist[ny][nx] = dist[cur.y][cur.x] + 1;
					q2.offer(new Point(ny, nx));
				} else {
					ans = Math.min(ans, dist[ny][nx] + dist[cur.y][cur.x]);
				}

			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
