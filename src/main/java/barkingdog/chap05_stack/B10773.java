package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B10773 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int k;
	private static Stack<Integer> stk = new Stack<>();
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		k = Integer.parseInt(br.readLine());

		while (k-- > 0) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				stk.pop();
				continue;
			}

			stk.push(num);
		}

		for (Integer num : stk) {
			ans += num;
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
