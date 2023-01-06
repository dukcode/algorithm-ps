package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1012 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;

	private static int w;
	private static int h;
	private static int n;

	private static int[][] board;
	private static boolean[][] vis;

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

		t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			ans = 0;
			q = new LinkedList<>();

			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			board = new int[h][w];
			vis = new boolean[h][w];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				board[y][x] = 1;

			}

			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					if (vis[y][x] || board[y][x] == 0) {
						continue;
					}

					ans++;
					q.offer(new Point(y, x));
					vis[y][x] = true;

					while (!q.isEmpty()) {
						Point cur = q.poll();

						for (int i = 0; i < 4; i++) {
							int ny = cur.y + dy[i];
							int nx = cur.x + dx[i];

							if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
								continue;
							}

							if (vis[ny][nx] || board[ny][nx] == 0) {
								continue;
							}

							vis[ny][nx] = true;
							q.offer(new Point(ny, nx));
						}
					}
				}
			}

			bw.write(String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
