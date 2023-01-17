package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16920 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int p;

	private static int[][] board;
	private static int[] s;

	private static class Point {
		int s;
		int y;
		int x;

		public Point(int s, int y, int x) {
			this.s = s;
			this.y = y;
			this.x = x;
		}

	}

	private static int[] dy = {-1, 0, 1, 0};
	private static int[] dx = {0, -1, 0, 1};

	private static List<Queue<Point>> qs = new ArrayList<>();
	private static int[] ansArr;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		s = new int[p + 1];

		st = new StringTokenizer(br.readLine());
		qs.add(null);
		for (int i = 1; i <= p; i++) {
			s[i] = Integer.parseInt(st.nextToken());
			qs.add(new LinkedList<>());
		}

		ansArr = new int[p + 1];

		for (int y = 0; y < h; y++) {
			String line = br.readLine();
			for (int x = 0; x < w; x++) {
				char block = line.charAt(x);
				if (Character.isDigit(block)) {
					int p = block - '0';
					ansArr[p]++;
					qs.get(p).offer(new Point(0, y, x));
					board[y][x] = p;
				} else if (block == '#') {
					board[y][x] = -1;
				}
			}
		}

		while (true) {
			boolean isExpanded = false;
			for (int player = 1; player <= p; ++player) {
				Queue<Point> q = qs.get(player);
				Queue<Point> nq = new LinkedList<>();

				while (!q.isEmpty()) {
					Point cur = q.poll();

					if (cur.s == s[player]) {
						nq.offer(new Point(0, cur.y, cur.x));
						continue;
					}

					for (int i = 0; i < 4; i++) {
						int ny = cur.y + dy[i];
						int nx = cur.x + dx[i];

						if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
							continue;
						}

						if (board[ny][nx] != 0) {
							continue;
						}

						board[ny][nx] = player;
						isExpanded = true;
						ansArr[player]++;
						q.offer(new Point(cur.s + 1, ny, nx));
					}
				}

				qs.set(player, nq);
			}

			if (!isExpanded) {
				break;
			}
		}

		for (int i = 1; i <= p; ++i) {
			bw.write(String.valueOf(ansArr[i]));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
