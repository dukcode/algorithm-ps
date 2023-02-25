package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7569 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int w;
	private static int d;
	private static int h;

	private static int[][][] board;

	private static class Point {
		int z;
		int y;
		int x;

		public Point(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dz = {0, 0, 0, 0, -1, 1};
	private static int[] dy = {1, 0, -1, 0, 0, 0};
	private static int[] dx = {0, 1, 0, -1, 0, 0};

	private static Queue<Point> q = new LinkedList<>();

	private static int remainCnt;
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		board = new int[h][d][w];

		for (int z = 0; z < h; z++) {
			for (int y = 0; y < d; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < w; x++) {
					int tomato = Integer.parseInt(st.nextToken());
					if (tomato == 1) {
						q.offer(new Point(z, y, x));
					} else if (tomato == 0) {
						remainCnt++;
					}
					board[z][y][x] = tomato;
				}
			}
		}

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 6; i++) {
				int nz = cur.z + dz[i];
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (nz < 0 || nz >= h || ny < 0 || ny >= d || nx < 0 || nx >= w) {
					continue;
				}

				if (board[nz][ny][nx] != 0) {
					continue;
				}

				remainCnt--;
				board[nz][ny][nx] = board[cur.z][cur.y][cur.x] + 1;
				ans = board[cur.z][cur.y][cur.x];

				q.offer(new Point(nz, ny, nx));
			}
		}

		bw.write(String.valueOf(remainCnt != 0 ? -1 : ans));

		br.close();
		bw.close();
	}

}
