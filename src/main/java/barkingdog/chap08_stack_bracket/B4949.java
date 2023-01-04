package barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B4949 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			String line = br.readLine();

			if (line.equals(".")) {
				break;
			}

			Stack<Character> stk = new Stack<>();
			boolean isBalanced = true;
			Loop:
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);

				switch (c) {
					case '(':
					case '[':
						stk.push(c);
						break;
					case ')':
						if (stk.isEmpty() || stk.peek() != '(') {
							isBalanced = false;
							break Loop;
						}
						stk.pop();
						break;
					case ']':
						if (stk.isEmpty() || stk.peek() != '[') {
							isBalanced = false;
							break Loop;
						}
						stk.pop();
						break;
					default:
						break;
				}
			}

			if (isBalanced && stk.isEmpty()) {
				bw.write("yes");
			} else {
				bw.write("no");
			}

			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
