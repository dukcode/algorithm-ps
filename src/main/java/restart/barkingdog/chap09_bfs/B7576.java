package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

	private static int[][] board;

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

	private static Queue<Point> q = new LinkedList<>();

	private static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				int tomato = Integer.parseInt(st.nextToken());
				if (tomato == 1) {
					q.offer(new Point(y, x));
				}

				board[y][x] = tomato;
			}
		}

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					continue;
				}

				if (board[ny][nx] != 0) {
					continue;
				}

				board[ny][nx] = board[cur.y][cur.x] + 1;
				q.offer(new Point(ny, nx));
			}
		}

		boolean finished = true;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (board[y][x] == 0) {
					finished = false;
				}
				ans = Math.max(ans, board[y][x] - 1);
			}
		}

		bw.write(String.valueOf(finished ? ans : -1));

		br.close();
		bw.close();
	}

}
