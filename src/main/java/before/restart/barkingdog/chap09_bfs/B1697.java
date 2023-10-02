package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1697 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static int[] dist = new int[200_001];

	private static Queue<Integer> q = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dist[n] = 1;
		q.offer(n);

		Loop:
		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == k) {
				bw.write(String.valueOf(dist[cur] - 1));
			}

			for (int next : new int[] {cur - 1, cur + 1, cur * 2}) {
				if (next < 0 || next > 200_000) {
					continue;
				}

				if (dist[next] != 0) {
					continue;
				}

				dist[next] = dist[cur] + 1;
				q.offer(next);
			}
		}

		br.close();
		bw.close();
	}

}
