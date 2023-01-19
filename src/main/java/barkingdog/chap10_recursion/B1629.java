package barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1629 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int a;
	private static int b;
	private static int c;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		bw.write(String.valueOf(func(a, b, c)));

		br.close();
		bw.close();
	}

	private static long func(int a, int b, int c) {
		if (b == 0) {
			return 1L;
		}

		long num = func(a, b / 2, c);
		num = (num * num) % c;

		return b % 2 == 0 ? num : num * a % c;
	}

}
