package before.restart.jongman.chap07_divide_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Fence {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] height;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			height = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}

			bw.write(String.valueOf(solve(0, n)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve(int st, int en) {
		if (st + 1 == en) {
			return height[st];
		}

		int mid = (st + en) / 2;
		int ret = Math.max(solve(st, mid), solve(mid, en));

		int l = mid - 1;
		int r = mid;
		int h = Math.min(height[l], height[r]);
		ret = Math.max(ret, h * 2);

		while (st < l || r < en - 1) {
			if (r < en - 1 && (l == st || height[l - 1] <= height[r + 1])) {
				h = Math.min(h, height[++r]);
			} else {
				h = Math.min(h, height[--l]);
			}

			ret = Math.max(ret, h * (r - l + 1));
		}

		return ret;
	}

}
