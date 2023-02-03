package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B11559 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static char[][] board = new char[12][6];

	private static int ans;

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

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int y = 0; y < 12; y++) {
			String line = br.readLine();
			for (int x = 0; x < 6; x++) {
				board[y][x] = line.charAt(x);
			}
		}

		while (pop()) {
			ans++;
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean pop() {
		boolean ret = false;
		Queue<Point> q = new LinkedList<>();
		boolean[][] vis = new boolean[12][6];

		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 6; x++) {
				if (board[y][x] == '.' || vis[y][x]) {
					continue;
				}

				List<Point> cands = new ArrayList<>();

				q.offer(new Point(y, x));
				vis[y][x] = true;
				cands.add(new Point(y, x));
				while (!q.isEmpty()) {
					Point cur = q.poll();

					for (int i = 0; i < 4; ++i) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6) {
							continue;
						}

						if (vis[ny][nx] || board[ny][nx] != board[cur.y][cur.x]) {
							continue;
						}

						vis[ny][nx] = true;
						cands.add(new Point(ny, nx));
						q.offer(new Point(ny, nx));
					}
				}

				if (cands.size() >= 4) {
					ret = true;
					for (Point cand : cands) {
						board[cand.y][cand.x] = '.';
					}
				}
			}
		}

		for (int x = 0; x < 6; ++x) {
			int pos = 11;
			for (int y = 11; y >= 0; --y) {
				if (board[y][x] == '.') {
					continue;
				}

				board[pos--][x] = board[y][x];
			}

			while (pos >= 0) {
				board[pos--][x] = '.';
			}
		}

		return ret;
	}

}
