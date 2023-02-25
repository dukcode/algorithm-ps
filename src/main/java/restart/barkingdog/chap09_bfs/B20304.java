package restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20304 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;
	private static int[] attempts;

	private static int digit;

	private static int[] dist;

	private static Queue<Integer> q = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		dist = new int[n + 1];

		int tmp = n;
		while (tmp > 0) {
			tmp = tmp >> 1;
			digit++;
		}

		attempts = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; ++i) {
			int num = Integer.parseInt(st.nextToken());
			dist[num] = 1;
			q.offer(num);
			attempts[i] = num;
		}
		for (int i = 0; i < digit; ++i) {
			int next = 10 ^ (1 << i);
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < digit; ++i) {
				int next = cur ^ (1 << i);

				if (next > n) {
					continue;
				}

				if (dist[next] != 0) {
					continue;
				}

				dist[next] = dist[cur] + 1;
				q.offer(next);
			}
		}

		ans = Arrays.stream(dist).max().getAsInt() - 1;
		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
