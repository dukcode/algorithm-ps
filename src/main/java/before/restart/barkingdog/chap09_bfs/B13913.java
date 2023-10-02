package before.restart.barkingdog.chap09_bfs;

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

public class B13913 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static int size;
	private static int[] dist;
	private static int[] from;

	private static Queue<Integer> q = new LinkedList<>();

	private static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		size = Math.max(n + 1, 2 * k + 1);
		dist = new int[size];
		from = new int[size];
		Arrays.fill(from, -1);

		dist[n] = 1;
		q.offer(n);

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == k) {
				break;
			}

			for (int next : new int[] {cur - 1, cur + 1, cur * 2}) {
				if (next < 0 || next >= size) {
					continue;
				}

				if (dist[next] != 0) {
					continue;
				}

				dist[next] = dist[cur] + 1;
				from[next] = cur;
				q.offer(next);
			}
		}

		int pos = k;
		while (pos != -1) {
			list.add(pos);
			pos = from[pos];
		}

		bw.write(String.valueOf(dist[k] - 1));
		bw.newLine();
		for (int i = list.size() - 1; i >= 0; --i) {
			bw.write(String.valueOf(list.get(i)));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
