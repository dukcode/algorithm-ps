package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14442 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int k;

	private static int[][] board;
	private static int[][][] dist;

	private static class Point {

		int k;
		int y;
		int x;

		public Point(int k, int y, int x) {
			this.k = k;
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {0, 0, -1, 1};
	private static int[] dx = {-1, 1, 0, 0};

	private static Queue<Point> q = new LinkedList<>();

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
		dist = new int[k + 1][h][w];
		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				board[y][x] = line.charAt(x) - '0';
			}
		}

		dist[k][0][0] = 1;
		q.offer(new Point(k, 0, 0));

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.y == h - 1 && cur.x == w - 1) {
				ans = dist[cur.k][cur.y][cur.x];
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					continue;
				}

				if (board[ny][nx] == 0) {
					if (dist[cur.k][ny][nx] != 0) {
						continue;
					}
					dist[cur.k][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
					q.offer(new Point(cur.k, ny, nx));
				} else {
					if (cur.k == 0 || dist[cur.k - 1][ny][nx] != 0) {
						continue;
					}
					dist[cur.k - 1][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
					q.offer(new Point(cur.k - 1, ny, nx));
				}
			}
		}

		bw.write(String.valueOf(ans == 0 ? -1 : ans));

		br.close();
		bw.close();
	}

}
