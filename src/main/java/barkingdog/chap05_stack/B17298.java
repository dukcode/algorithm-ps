package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static class Number {
		int num;
		int idx;

		public Number(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

	private static Stack<Number> stk = new Stack<>();
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			int num = Integer.parseInt(st.nextToken());

			while (!stk.isEmpty() && stk.peek().num < num) {
				arr[stk.pop().idx] = num;
			}

			stk.push(new Number(num, idx));
		}

		for (int num : arr) {
			bw.write(String.valueOf(num == 0 ? -1 : num));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
