package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5427 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;

	private static int w;
	private static int h;

	private static char[][] board;
	private static int[][] pDist;
	private static int[][] fDist;

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

	private static Queue<Point> pq;
	private static Queue<Point> fq;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			board = new char[h][w];
			pDist = new int[h][w];
			fDist = new int[h][w];

			ans = 0;
			pq = new LinkedList<>();
			fq = new LinkedList<>();

			for (int y = 0; y < h; y++) {
				String line = br.readLine();
				for (int x = 0; x < w; x++) {
					char c = line.charAt(x);

					board[y][x] = c;
					switch (c) {
						case '@':
							pDist[y][x] = 1;
							pq.offer(new Point(y, x));
							break;
						case '*':
							fDist[y][x] = 1;
							fq.offer(new Point(y, x));
							break;
						default:
							break;
					}
				}
			}

			while (!fq.isEmpty()) {
				Point cur = fq.poll();

				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];

					if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
						continue;
					}

					if (board[ny][nx] == '#' || fDist[ny][nx] != 0) {
						continue;
					}

					fDist[ny][nx] = fDist[cur.y][cur.x] + 1;
					fq.offer(new Point(ny, nx));
				}
			}

			Loop:
			while (!pq.isEmpty()) {
				Point cur = pq.poll();

				for (int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];

					if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
						ans = pDist[cur.y][cur.x];
						break Loop;
					}

					if (board[ny][nx] == '#'
						|| (fDist[ny][nx] != 0 && fDist[ny][nx] <= pDist[cur.y][cur.x] + 1)
						|| pDist[ny][nx] != 0) {
						continue;
					}

					pDist[ny][nx] = pDist[cur.y][cur.x] + 1;
					pq.offer(new Point(ny, nx));
				}
			}

			bw.write(ans == 0 ? "IMPOSSIBLE" : String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}
}
