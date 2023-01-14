package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int k;

	private static int w;
	private static int h;

	private static int[][] board;
	private static int[][][] dist;

	private static class Point {
		int y;
		int x;
		int k;

		public Point(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
	}

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	private static int[] hy = {-2, -2, -1, -1, 1, 1, 2, 2};
	private static int[] hx = {-1, 1, -2, 2, -2, 2, -1, 1};

	private static Queue<Point> q = new LinkedList<>();

	private static int ans = -1;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		dist = new int[k + 1][h][w];
		q.offer(new Point(0, 0, k));
		dist[k][0][0] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.y == h - 1 && cur.x == w - 1) {
				ans = dist[cur.k][cur.y][cur.x] - 1;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (outOfBound(ny, nx)) {
					continue;
				}

				if (dist[cur.k][ny][nx] != 0 || board[ny][nx] == 1) {
					continue;
				}

				dist[cur.k][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
				q.offer(new Point(ny, nx, cur.k));
			}

			if (cur.k == 0) {
				continue;
			}

			for (int i = 0; i < 8; i++) {
				int ny = cur.y + hy[i];
				int nx = cur.x + hx[i];

				if (outOfBound(ny, nx)) {
					continue;
				}

				if (dist[cur.k - 1][ny][nx] != 0 || board[ny][nx] == 1) {
					continue;
				}

				dist[cur.k - 1][ny][nx] = dist[cur.k][cur.y][cur.x] + 1;
				q.offer(new Point(ny, nx, cur.k - 1));
			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean outOfBound(int ny, int nx) {
		return ny < 0 || ny >= h || nx < 0 || nx >= w;
	}

}
