package restart.barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B3986 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			String w = br.readLine();
			Stack<Character> stk = new Stack<>();

			for (int i = 0; i < w.length(); i++) {
				char c = w.charAt(i);

				if (!stk.isEmpty() && stk.peek() == c) {
					stk.pop();
					continue;
				}
				stk.push(c);
			}

			if (stk.isEmpty()) {
				ans++;
			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
