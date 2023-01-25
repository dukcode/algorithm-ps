package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18809 {
	private static final int FLOWER = 123456789;
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int h;
	private static int w;
	private static int[][] board;    // 0 호수, 1 황토, 2 그냥 땅

	private static int g;
	private static int r;

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

	private static List<Point> candidates = new ArrayList<>();

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		board = new int[h][w];
		for (int y = 0; y < h; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				int ground = Integer.parseInt(st.nextToken());
				if (ground == 2) {
					candidates.add(new Point(y, x));
				}
				board[y][x] = ground;
			}
		}

		int[] permutation = new int[candidates.size()];
		Arrays.fill(permutation, 0, g, -1);
		Arrays.fill(permutation, permutation.length - r, permutation.length, 1);

		do {
			int[][] dist = new int[h][w];
			int count = 0;
			Queue<Point> q = new LinkedList<>();
			for (int i = 0; i < candidates.size(); ++i) {
				if (permutation[i] != 0) {
					Point p = candidates.get(i);
					dist[p.y][p.x] = permutation[i];
					q.offer(p);
				}
			}

			while (!q.isEmpty()) {
				Point cur = q.poll();

				if (dist[cur.y][cur.x] == FLOWER) {
					continue;
				}

				int nextDist = dist[cur.y][cur.x] + (dist[cur.y][cur.x] > 0 ? 1 : -1);

				for (int i = 0; i < 4; ++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];

					if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
						continue;
					}

					if (board[ny][nx] == 0) {
						continue;
					}

					if (dist[ny][nx] != 0) {
						if (dist[ny][nx] + nextDist == 0) {
							count++;
							dist[ny][nx] = FLOWER;
						}
						continue;

					}

					dist[ny][nx] = nextDist;
					q.offer(new Point(ny, nx));
				}
			}

			ans = Math.max(ans, count);

		} while (nextPermutation(permutation));

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean nextPermutation(int[] arr) {

		int leftIdx = arr.length - 2;
		while (leftIdx >= 0 && arr[leftIdx] >= arr[leftIdx + 1]) {
			leftIdx--;
		}

		if (leftIdx == -1) {
			return false;
		}

		int rightIdx = arr.length - 1;
		while (arr[leftIdx] >= arr[rightIdx]) {
			rightIdx--;
		}

		swap(arr, leftIdx, rightIdx);

		int st = leftIdx + 1;
		int en = arr.length - 1;
		while (st < en) {
			swap(arr, st, en);
			st++;
			en--;
		}

		return true;
	}

	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
