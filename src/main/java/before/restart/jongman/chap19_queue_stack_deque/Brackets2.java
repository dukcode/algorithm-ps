package before.restart.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Brackets2 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			String brackets = br.readLine();
			bw.write(judge(brackets) ? "YES" : "NO");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static boolean judge(String brackets) {
		Stack<Character> stk = new Stack<>();
		for (char c : brackets.toCharArray()) {
			switch (c) {
				case '(':
				case '[':
				case '{':
					stk.push(c);
					break;
				case ')':
				case ']':
				case '}':
					if (stk.isEmpty() || stk.peek() != counter(c)) {
						return false;
					}
					stk.pop();
					break;
				default:
					break;
			}
		}

		return stk.isEmpty();
	}

	private static char counter(char c) {
		if (c == ')') {
			return '(';
		}

		if (c == '}') {
			return '{';
		}

		if (c == ']') {
			return '[';
		}

		return 'X';
	}

}
