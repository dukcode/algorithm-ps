package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9328 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;

	private static int h;
	private static int w;

	private static char[][] board;
	private static boolean[][] vis;

	private static boolean[] keys;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	private static Queue<Point> q;
	private static Queue<Point> nq;

	private static int cnt;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());

		while (t-- > 0) {

			cnt = 0;
			input();

			q = new LinkedList<>();
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					if (y == 0 || y == h - 1 || x == 0 || x == w - 1) {
						char block = board[y][x];

						if (block == '*') {
							continue;
						}

						vis[y][x] = true;
						q.offer(new Point(y, x));
					}
				}
			}

			while (true) {
				boolean opened = false;
				boolean getKey = false;
				nq = new LinkedList<>();
				while (!q.isEmpty()) {
					Point cur = q.poll();
					char curBlock = board[cur.y][cur.x];

					if (Character.isUpperCase(curBlock)) {
						if (keys[curBlock - 'A']) {
							board[cur.y][cur.x] = '.';
							opened = true;
						} else {
							nq.offer(new Point(cur.y, cur.x));
							continue;
						}
					} else if (curBlock == '$') {
						board[cur.y][cur.x] = '.';
						cnt++;
					} else if (Character.isLowerCase(curBlock)) {
						board[cur.y][cur.x] = '.';
						keys[curBlock - 'a'] = true;
						getKey = true;
					}

					for (int i = 0; i < 4; ++i) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
							continue;
						}

						if (vis[ny][nx] || board[ny][nx] == '*') {
							continue;
						}

						vis[ny][nx] = true;
						q.offer(new Point(ny, nx));
					}
				}

				if (!opened && !getKey) {
					break;
				}
				q.addAll(nq);
			}

			bw.write(String.valueOf(cnt));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		vis = new boolean[h][w];
		board = new char[h][w];
		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				char block = line.charAt(x);
				board[y][x] = block;
			}
		}

		keys = new boolean[26];
		String line = br.readLine();
		if (!line.startsWith("0")) {
			for (int i = 0; i < line.length(); ++i) {
				int key = line.charAt(i) - 'a';
				keys[key] = true;
			}
		}

	}
}
