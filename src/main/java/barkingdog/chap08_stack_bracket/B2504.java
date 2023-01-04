package barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B2504 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static Stack<Character> stk = new Stack<>();
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = br.readLine();

		int cnt = 1;
		boolean isBalanced = true;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c == '(' || c == '[') {
				stk.push(c);
				cnt *= c == '(' ? 2 : 3;
				continue;
			}

			char counter = c == ')' ? '(' : '[';

			if (stk.isEmpty() || stk.peek() != counter) {
				isBalanced = false;
				break;
			}

			if (line.charAt(i - 1) == counter) {
				ans += cnt;
			}

			cnt /= c == ')' ? 2 : 3;
			stk.pop();
		}

		bw.write(String.valueOf(isBalanced && stk.isEmpty() ? ans : 0));

		br.close();
		bw.close();
	}

}
