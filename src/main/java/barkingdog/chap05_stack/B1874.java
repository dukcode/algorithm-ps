package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B1874 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static Stack<Integer> stk = new Stack<>();

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		int i = 1;
		stk.push(0);
		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine());

			while (stk.peek() < num) {
				sb.append("+\n");
				stk.push(i++);
			}

			if (stk.peek() == num) {
				sb.append("-\n");
				stk.pop();
				continue;
			}

			if (stk.peek() > num) {
				sb = new StringBuilder("NO");
				break;
			}
		}

		bw.write(sb.toString());

		br.close();
		bw.close();
	}

}
