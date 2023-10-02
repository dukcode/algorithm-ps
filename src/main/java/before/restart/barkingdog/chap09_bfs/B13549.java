package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B13549 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static int size;
	private static int[] dist;

	private static Deque<Integer> dq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		size = Math.max(n + 1, 2 * k + 1);
		dist = new int[size];

		dq.offerLast(n);
		dist[n] = 1;

		while (dist[k] == 0) {
			int cur = dq.pollFirst();

			if (2 * cur < size && dist[2 * cur] == 0) {
				dist[2 * cur] = dist[cur];
				dq.offerFirst(2 * cur);
			}

			for (int next : new int[] {cur - 1, cur + 1}) {
				if (next < 0 || next >= size) {
					continue;
				}

				if (dist[next] != 0) {
					continue;
				}

				dist[next] = dist[cur] + 1;
				dq.offerLast(next);
			}
		}

		bw.write(String.valueOf(dist[k] - 1));

		br.close();
		bw.close();
	}

	private static void teleport(int pos) {
		int d = dist[pos];
		int cur = pos;
		while (true) {
			cur *= 2;
			if (cur >= size) {
				break;
			}

			if (dist[cur] != 0) {
				break;
			}

			dist[cur] = d;
			dq.offer(cur);
		}
	}

}
