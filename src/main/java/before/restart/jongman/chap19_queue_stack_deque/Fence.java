package before.restart.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Fence {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] h;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			h = new int[n + 1];
			for (int i = 0; i < n; ++i) {
				h[i] = Integer.parseInt(st.nextToken());
			}

			bw.write(String.valueOf(solve()));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solve() {
		int ret = 0;
		Stack<Integer> stk = new Stack<>();
		for (int i = 0; i <= n; ++i) {
			while (!stk.isEmpty() && h[stk.peek()] >= h[i]) {
				int heightIdx = stk.pop();
				int width = stk.isEmpty() ? i : (i - stk.peek() - 1);
				ret = Math.max(ret, h[heightIdx] * width);
			}
			stk.push(i);
		}

		return ret;
	}

}
