package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

	private static int[][] board;

	private static int[][][] dist;

	private static class Point {
		int y;
		int x;
		int cnt;

		public Point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
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
		board = new int[h][w];
		dist = new int[2][h][w];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				int block = line.charAt(x) - '0';
				board[y][x] = block;
			}
		}

		q.offer(new Point(0, 0, 1));
		dist[1][0][0] = 1;

		int ansCnt = -1;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.y == h - 1 && cur.x == w - 1) {
				ansCnt = cur.cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				int cnt = cur.cnt;

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					continue;
				}

				if (board[ny][nx] == 1) {
					if (cnt == 1) {
						if (dist[0][ny][nx] != 0) {
							continue;
						}
						dist[0][ny][nx] = dist[1][cur.y][cur.x] + 1;
						q.offer(new Point(ny, nx, 0));
					}
				} else {
					if (dist[cnt][ny][nx] != 0) {
						continue;
					}
					dist[cnt][ny][nx] = dist[cnt][cur.y][cur.x] + 1;
					q.offer(new Point(ny, nx, cnt));
				}
			}
		}

		bw.write(ansCnt == -1 ? String.valueOf(-1) : String.valueOf(dist[ansCnt][h - 1][w - 1]));

		br.close();
		bw.close();
	}

}
