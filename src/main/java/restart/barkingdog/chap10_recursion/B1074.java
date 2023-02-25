package restart.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1074 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int r;
	private static int c;

	private static int cnt;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		bw.write(String.valueOf(func(1 << n)));

		br.close();
		bw.close();
	}

	private static int func(int size) {
		if (size == 0) {
			return 0;
		}

		int half = size / 2;
		int quarterCount = half * half;
		if (r < half && c < half) {
			return func(half);
		}

		if (r < half && half <= c) {
			return quarterCount + func(half);
		}

		if (r >= half && half > c) {
			return 2 * quarterCount + func(half);
		}

		return 3 * quarterCount + func(half);
	}

}
