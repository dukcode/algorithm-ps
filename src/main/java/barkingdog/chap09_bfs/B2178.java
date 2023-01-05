package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2178 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

	private static int[][] board;
	private static int[][] dis;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {0, 0, -1, 1};
	private static int[] dx = {1, -1, 0, 0};

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
		dis = new int[h][w];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				int i = line.charAt(x) - '0';
				board[y][x] = i;
			}
		}

		q = new LinkedList<>();
		q.offer(new Point(0, 0));
		dis[0][0] = 1;

		Loop:
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					continue;
				}

				if (board[ny][nx] != 1 || dis[ny][nx] != 0) {
					continue;
				}

				if (ny == h - 1 && nx == w - 1) {
					ans = dis[cur.y][cur.x] + 1;
					break Loop;
				}

				dis[ny][nx] = dis[cur.y][cur.x] + 1;
				q.offer(new Point(ny, nx));
			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
