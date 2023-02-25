package restart.barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B9012 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static Stack<Character> stk;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			String str = br.readLine();

			stk = new Stack<>();
			boolean isVps = true;

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);

				if (c == '(') {
					stk.push(c);
					continue;
				}

				if (c == ')') {
					if (stk.isEmpty()) {
						isVps = false;
						break;
					}
					stk.pop();
				}
			}

			if (isVps && stk.isEmpty()) {
				bw.write("YES");
			} else {
				bw.write("NO");
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
