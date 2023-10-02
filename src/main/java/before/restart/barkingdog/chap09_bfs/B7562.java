package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;
	private static int i;
	private static int[][] dist;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
	private static int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};

	private static Queue<Point> q;
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			q = new LinkedList<>();
			ans = 0;

			i = Integer.parseInt(br.readLine());
			dist = new int[i][i];
			Point start = parsePoint();
			Point end = parsePoint();

			dist[start.y][start.x] = 1;
			q.offer(start);

			while (!q.isEmpty()) {
				Point cur = q.poll();

				if (cur.y == end.y && cur.x == end.x) {
					ans = dist[cur.y][cur.x] - 1;
					break;
				}

				for (int j = 0; j < 8; j++) {
					int ny = cur.y + dy[j];
					int nx = cur.x + dx[j];

					if (ny < 0 || ny >= i || nx < 0 || nx >= i) {
						continue;
					}

					if (dist[ny][nx] != 0) {
						continue;
					}

					dist[ny][nx] = dist[cur.y][cur.x] + 1;
					q.offer(new Point(ny, nx));
				}
			}

			bw.write(String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static Point parsePoint() throws IOException {
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		return new Point(y, x);
	}

}
