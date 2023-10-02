package before.restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2493 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static class Tower {
		int h;
		int idx;

		public Tower(int h, int idx) {
			this.h = h;
			this.idx = idx;
		}
	}

	private static Stack<Tower> stk = new Stack<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			int h = Integer.parseInt(st.nextToken());
			Tower t = new Tower(h, idx);

			while (!stk.isEmpty() && stk.peek().h < t.h) {
				stk.pop();
			}

			int i = stk.isEmpty() ? 0 : stk.peek().idx + 1;
			bw.write(String.valueOf(i));
			bw.write(' ');

			stk.push(t);

		}

		br.close();
		bw.close();
	}

}
