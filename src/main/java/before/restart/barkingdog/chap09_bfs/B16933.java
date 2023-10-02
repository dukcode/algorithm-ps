package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16933 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int k;

	private static int[][] board;
	private static int[][][][] dist; // status, k, y, x

	private static class Point {
		int s;    // status
		int k;
		int y;
		int x;

		public Point(int s, int k, int y, int x) {
			this.s = s;
			this.k = k;
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	private static final int DAY = 0;
	private static final int NIGHT = 1;

	private static Queue<Point> q = new LinkedList<>();

	private static int ans = -1;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				board[y][x] = line.charAt(x) - '0';
			}
		}

		dist = new int[2][k + 1][h][w];
		dist[DAY][k][0][0] = 1;
		q.offer(new Point(DAY, k, 0, 0));

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int ns = cur.s == DAY ? NIGHT : DAY;

			if (cur.y == h - 1 && cur.x == w - 1) {
				ans = dist[cur.s][cur.k][cur.y][cur.x];
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
					continue;
				}

				if (board[ny][nx] == 0) {
					// 공간 낮 -> 이동
					// 공간 밤 -> 이동
					if (dist[ns][cur.k][ny][nx] != 0) {
						continue;
					}

					dist[ns][cur.k][ny][nx] = dist[cur.s][cur.k][cur.y][cur.x] + 1;
					q.offer(new Point(ns, cur.k, ny, nx));
				} else {
					if (cur.s == DAY) {
						// 벽 낮 -> 부수기
						if (cur.k == 0 || dist[ns][cur.k][ny][nx] != 0) {
							continue;
						}

						dist[ns][cur.k - 1][ny][nx] = dist[cur.s][cur.k][cur.y][cur.x] + 1;
						q.offer(new Point(ns, cur.k - 1, ny, nx));
					} else {
						// 벽 밤 -> 대기
						if (dist[ns][cur.k][cur.y][cur.x] != 0) {
							continue;
						}

						dist[ns][cur.k][cur.y][cur.x] = dist[cur.s][cur.k][cur.y][cur.x] + 1;
						q.offer(new Point(ns, cur.k, cur.y, cur.x));
					}
				}

			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
