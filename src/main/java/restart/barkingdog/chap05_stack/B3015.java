package restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B3015 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;

	private static class Person {
		int h;
		int d;

		public Person(int h, int d) {
			this.h = h;
			this.d = d;
		}
	}

	private static Stack<Person> stk = new Stack<>();
	private static long ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			int h = Integer.parseInt(br.readLine());

			int duplicate = 1;
			while (!stk.isEmpty() && stk.peek().h <= h) {

				if (stk.peek().h == h) {
					duplicate += stk.peek().d;
				}
				ans += stk.peek().d;
				stk.pop();
			}

			ans += stk.isEmpty() ? 0 : 1;
			stk.push(new Person(h, duplicate));

		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
