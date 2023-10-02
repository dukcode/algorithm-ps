package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179 {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;

	private static int[][] fDist;    // 벽, 불
	private static int[][] pDist;    // 지훈

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

	private static Queue<Point> fq = new LinkedList<>();
	private static Queue<Point> pq = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		fDist = new int[h][w];
		pDist = new int[h][w];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				char c = line.charAt(x);

				switch (c) {
					case '#':
						fDist[y][x] = -1;
						break;
					case 'J':
						pDist[y][x] = 1;
						pq.offer(new Point(y, x));
						break;
					case 'F':
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

				if (outOfBound(ny, nx)) {
					continue;
				}

				if (fDist[ny][nx] != 0) {
					continue;
				}

				fDist[ny][nx] += fDist[cur.y][cur.x] + 1;
				fq.offer(new Point(ny, nx));
			}
		}

		Loop:
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (outOfBound(ny, nx)) {
					ans = pDist[cur.y][cur.x];
					break Loop;
				}

				int nextTime = pDist[cur.y][cur.x] + 1;
				if (fDist[ny][nx] == -1 || (fDist[ny][nx] != 0 && fDist[ny][nx] <= nextTime) || pDist[ny][nx] != 0) {
					continue;
				}

				pDist[ny][nx] = nextTime;
				pq.offer(new Point(ny, nx));
			}
		}

		bw.write(ans == 0 ? "IMPOSSIBLE" : String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean outOfBound(int y, int x) {
		return y < 0 || y >= h || x < 0 || x >= w;
	}
}
