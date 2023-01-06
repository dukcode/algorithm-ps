package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2583 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int n;

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

	private static int cnt;
	private static List<Integer> areas = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					board[y][x] = -1;
				}
			}
		}

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (board[y][x] != 0) {
					continue;
				}

				cnt++;
				int area = 1;
				board[y][x] = 1;
				q.offer(new Point(y, x));

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

						area++;
						board[ny][nx] = 1;
						q.offer(new Point(ny, nx));
					}
				}

				areas.add(area);
			}
		}

		bw.write(String.valueOf(cnt));
		bw.newLine();

		Collections.sort(areas);
		for (Integer area : areas) {
			bw.write(String.valueOf(area));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
