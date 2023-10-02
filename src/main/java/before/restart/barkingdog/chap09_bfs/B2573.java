package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

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

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int count;
		while (true) {
			ans++;
			melt();
			if ((count = count()) != 1) {
				break;
			}
		}

		bw.write(String.valueOf(count > 1 ? ans : 0));

		br.close();
		bw.close();
	}

	public static int count() {
		int count = 0;
		vis = new boolean[h][w];
		q = new LinkedList<>();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (vis[y][x] || board[y][x] == 0) {
					continue;
				}

				count++;
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

						q.offer(new Point(ny, nx));
						vis[ny][nx] = true;
					}
				}
			}
		}

		return count;
	}

	public static void melt() {
		int[][] boardAfter = new int[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (board[y][x] == 0) {
					continue;
				}

				int count = 0;
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];

					if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
						continue;
					}

					if (board[ny][nx] != 0) {
						continue;
					}

					count++;
				}

				boardAfter[y][x] = Math.max(0, board[y][x] - count);
			}
		}

		board = boardAfter;
	}

}
