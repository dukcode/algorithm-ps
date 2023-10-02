package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B6593 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int d;
	private static int w;

	private static int[][][] board;
	private static int[][][] dist;

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

	private static int[] dz = {-1, 0, 0, 0, 0, 1};
	private static int[] dy = {0, -1, 1, 0, 0, 0};
	private static int[] dx = {0, 0, 0, -1, 1, 0};

	private static Queue<Point> q;

	private static Point end;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			if (h == 0) {
				break;
			}

			board = new int[h][d][w];
			dist = new int[h][d][w];
			q = new LinkedList<>();

			for (int z = 0; z < h; ++z) {
				for (int y = 0; y < d; ++y) {
					String line = br.readLine();
					for (int x = 0; x < w; ++x) {
						char c = line.charAt(x);
						if (c == 'S') {
							q.offer(new Point(z, y, x));
							board[z][y][x] = 0;
							dist[z][y][x] = 1;
						} else if (c == 'E') {
							end = new Point(z, y, x);
							board[z][y][x] = 0;
						} else if (c == '#') {
							board[z][y][x] = 1;
						} else {
							board[z][y][x] = 0;
						}
					}
				}
				br.readLine();
			}

			boolean isPossible = false;

			Loop:
			while (!q.isEmpty()) {
				Point cur = q.poll();

				for (int i = 0; i < 6; i++) {
					int nz = cur.z + dz[i];
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];

					if (nz < 0 || nz >= h || ny < 0 || ny >= d || nx <= 0 || nx >= w) {
						continue;
					}

					if (dist[nz][ny][nx] != 0 || board[nz][ny][nx] == 1) {
						continue;
					}

					if (nz == end.z && ny == end.y && nx == end.x) {
						isPossible = true;
						ans = dist[cur.z][cur.y][cur.x];
						break Loop;
					}

					dist[nz][ny][nx] = dist[cur.z][cur.y][cur.x] + 1;
					q.offer(new Point(nz, ny, nx));
				}
			}

			bw.write(isPossible ? "Escaped in " + ans + " minute(s)." : "Trapped!");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
