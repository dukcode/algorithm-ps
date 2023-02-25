package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5014 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int f;    // 전체 층
	private static int s;    // 현재 층
	private static int g;    // 목적지
	private static int u;    // 위로 갈 때 단위
	private static int d;    // 아래로 갈 때 단위

	private static int[] dist;

	private static Queue<Integer> q = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		dist = new int[f + 1];

		dist[s] = 1;
		q.offer(s);
		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == g) {
				ans = dist[cur] - 1;
				break;
			}

			for (int next : new int[] {cur - d, cur + u}) {
				if (next < 1 || next > f) {
					continue;
				}

				if (dist[next] != 0) {
					continue;
				}

				dist[next] = dist[cur] + 1;
				q.offer(next);
			}
		}

		bw.write(dist[g] != 0 ? String.valueOf(ans) : "use the stairs");

		br.close();
		bw.close();
	}

}
