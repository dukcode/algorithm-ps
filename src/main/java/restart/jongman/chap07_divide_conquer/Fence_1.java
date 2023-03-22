package restart.jongman.chap07_divide_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Fence_1 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] arr;

	private static class Rectangle {
		int h;
		int idx;

		public Rectangle(int h, int idx) {
			this.h = h;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			bw.write(String.valueOf(solve()));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve() {
		int ret = 0;
		Stack<Rectangle> stk = new Stack<>();
		for (int i = 0; i < n; i++) {
			int h = arr[i];

			int idx = i;
			while (!stk.isEmpty() && stk.peek().h > h) {
				Rectangle r = stk.pop();
				ret = Math.max(ret, r.h * (i - r.idx));
				idx = r.idx;
			}

			stk.push(new Rectangle(h, idx));
		}

		while (!stk.isEmpty()) {
			Rectangle r = stk.pop();
			ret = Math.max(ret, r.h * (n - r.idx));
		}

		return ret;
	}

}
