package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B16987 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[] s;
	private static int[] w;

	private static int cnt;
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		s = new int[n];
		w = new int[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			w[i] = Integer.parseInt(st.nextToken());
		}

		func(0);

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static void func(int idx) {
		if (idx == n || cnt == n - 1) {
			ans = Math.max(cnt, ans);
			return;
		}

		if (s[idx] <= 0) {
			func(idx + 1);
			return;
		}

		for (int i = 0; i < n; ++i) {
			if (i == idx || s[i] <= 0) {
				continue;
			}

			s[i] -= w[idx];
			s[idx] -= w[i];

			cnt += s[i] <= 0 ? 1 : 0;
			cnt += s[idx] <= 0 ? 1 : 0;

			func(idx + 1);

			cnt -= s[i] <= 0 ? 1 : 0;
			cnt -= s[idx] <= 0 ? 1 : 0;

			s[i] += w[idx];
			s[idx] += w[i];
		}
	}

}
