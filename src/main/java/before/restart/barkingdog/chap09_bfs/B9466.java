package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B9466 {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;

	private static int n;
	private static int[] arr;
	private static int[] taken;

	private static final int TAKEN = -1;
	private static final int NOT_VISITED = 0;

	private static long ans;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n + 1];
			taken = new int[n + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Loop:
			for (int i = 1; i <= n; ++i) {
				if (taken[arr[i]] != NOT_VISITED) {
					continue;
				}

				int cur = arr[i];
				while (true) {
					taken[cur] = i;
					cur = arr[cur];

					if (taken[cur] == i) {
						while (taken[cur] != TAKEN) {
							taken[cur] = TAKEN;
							cur = arr[cur];
						}
						continue Loop;
					} else if (taken[cur] != NOT_VISITED) {
						continue Loop;
					}
				}
			}

			ans = Arrays.stream(taken)
				.filter(t -> t != TAKEN)
				.count() - 1;

			bw.write(String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
