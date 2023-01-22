package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class B1941 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static String[] board;

	private static int[] arr;
	private static boolean[] taken = new boolean[25];

	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		board = new String[5];
		for (int i = 0; i < 5; ++i) {
			board[i] = br.readLine();
		}

		arr = new int[7];
		func(0, 0, 0);

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static void func(int idx, int startIdx, int countS) {
		if (idx == 7) {
			if (countS < 4) {
				return;
			}
			if (bfs()) {
				ans++;
			}
			return;
		}

		for (int i = startIdx; i < 25; ++i) {
			arr[idx] = i;
			taken[i] = true;
			func(idx + 1, i + 1, countS + (board[i / 5].charAt(i % 5) == 'S' ? 1 : 0));
			taken[i] = false;
		}
	}

	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[25];
		q.offer(arr[0]);
		visited[arr[0]] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			count++;

			for (int i = 0; i < 4; ++i) {
				int ny = cur / 5 + dy[i];
				int nx = cur % 5 + dx[i];

				if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
					continue;
				}

				if (visited[5 * ny + nx] || !taken[5 * ny + nx]) {
					continue;
				}

				visited[5 * ny + nx] = true;
				q.offer(5 * ny + nx);
			}
		}

		return count == 7;
	}

}
