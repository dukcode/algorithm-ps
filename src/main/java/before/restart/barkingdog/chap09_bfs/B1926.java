package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1926 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int[][] board;
	private static boolean[][] vis;
	private static int h;
	private static int w;

	private static class Point {

		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {0, 0, 1, -1};
	private static int[] dx = {1, -1, 0, 0};

	private static Queue<Point> q;

	private static int maxArea;
	private static int count;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		board = new int[h][w];
		vis = new boolean[h][w];

		for (int y = 0; y < h; ++y) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; ++x) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {

				if (vis[y][x] || board[y][x] == 0) {
					continue;
				}

				q = new LinkedList<>();
				q.add(new Point(y, x));

				vis[y][x] = true;
				count++;
				int area = 1;

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
						area++;
						q.add(new Point(ny, nx));
					}
				}

				maxArea = Math.max(maxArea, area);

			}
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.write(String.valueOf(maxArea));

		br.close();
		bw.close();
	}

}
