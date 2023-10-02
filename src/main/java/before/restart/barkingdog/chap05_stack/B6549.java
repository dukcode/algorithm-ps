package before.restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B6549 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static class Rectangle {
		long h;
		int idx;

		public Rectangle(long h, int idx) {
			this.h = h;
			this.idx = idx;
		}
	}

	private static Stack<Rectangle> stk;
	private static long ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			if (n == 0) {
				break;
			}

			stk = new Stack<>();
			ans = 0L;

			for (int i = 0; i < n; i++) {
				int h = Integer.parseInt(st.nextToken());

				int idx = i;
				while (!stk.isEmpty() && stk.peek().h >= h) {
					ans = Math.max(ans, stk.peek().h * (i - stk.peek().idx));
					idx = stk.peek().idx;
					stk.pop();
				}

				stk.push(new Rectangle(h, idx));
			}

			while (!stk.isEmpty()) {
				Rectangle r = stk.pop();
				ans = Math.max(ans, (n - r.idx) * r.h);
			}

			bw.write(String.valueOf(ans));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}
