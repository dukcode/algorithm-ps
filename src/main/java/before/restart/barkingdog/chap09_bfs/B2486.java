package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2486 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

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

	private static int maxHeight = Integer.MIN_VALUE;
	private static int minHeight = Integer.MAX_VALUE;

	private static int ans = 1;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				int height = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, height);
				minHeight = Math.min(minHeight, height);
				board[y][x] = height;
			}
		}

		for (int h = minHeight; h < maxHeight; ++h) {
			int cnt = 0;
			visited = new boolean[n][n];

			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (visited[y][x] || board[y][x] <= h) {
						continue;
					}

					cnt++;
					q = new LinkedList<>();
					visited[y][x] = true;
					q.offer(new Point(y, x));

					while (!q.isEmpty()) {
						Point cur = q.poll();
						for (int i = 0; i < 4; i++) {
							int ny = cur.y + dy[i];
							int nx = cur.x + dx[i];

							if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
								continue;
							}

							if (visited[ny][nx] || board[ny][nx] <= h) {
								continue;
							}

							visited[ny][nx] = true;
							q.offer(new Point(ny, nx));
						}
					}

				}
			}

			ans = Math.max(ans, cnt);
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
