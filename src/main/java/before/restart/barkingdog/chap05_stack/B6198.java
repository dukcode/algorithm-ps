package before.restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B6198 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;

	private static Stack<Long> stk = new Stack<>();
	private static long ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		for (int idx = 0; idx < n; idx++) {
			long h = Long.parseLong(br.readLine());

			while (!stk.isEmpty() && stk.peek() <= h) {
				stk.pop();
			}

			ans += stk.size();
			stk.push(h);
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
