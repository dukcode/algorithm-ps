package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class B10026 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static int[][] board;
	private static boolean[][] visited;

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

	private static Queue<Point> q;
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		board = new int[n][n];
		visited = new boolean[n][n];
		for (int y = 0; y < n; y++) {
			String line = br.readLine();
			for (int x = 0; x < n; x++) {
				char c = line.charAt(x);

				switch (c) {
					case 'R':
						board[y][x] = -1;
						break;
					case 'G':
						board[y][x] = 1;
						break;
					case 'B':
						board[y][x] = 0;
						break;
				}
			}
		}

		q = new LinkedList<>();
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (visited[y][x]) {
					continue;
				}

				visited[y][x] = true;
				int pivot = board[y][x];
				q.offer(new Point(y, x));
				ans++;

				while (!q.isEmpty()) {
					Point cur = q.poll();
					for (int i = 0; i < 4; i++) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
							continue;
						}

						if (visited[ny][nx] || board[ny][nx] != pivot) {
							continue;
						}

						visited[ny][nx] = true;
						q.offer(new Point(ny, nx));
					}
				}

			}
		}

		bw.write(String.valueOf(ans));
		bw.write(' ');

		visited = new boolean[n][n];
		q = new LinkedList<>();
		ans = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (visited[y][x]) {
					continue;
				}

				visited[y][x] = true;
				int pivot = Math.abs(board[y][x]);
				q.offer(new Point(y, x));
				ans++;

				while (!q.isEmpty()) {
					Point cur = q.poll();
					for (int i = 0; i < 4; i++) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
							continue;
						}

						if (visited[ny][nx] || Math.abs(board[ny][nx]) != pivot) {
							continue;
						}

						visited[ny][nx] = true;
						q.offer(new Point(ny, nx));
					}
				}

			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
