package restart.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B11729 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		bw.write(String.valueOf(func(n, 1, 3)));
		bw.newLine();
		bw.write(sb.toString());

		br.close();
		bw.close();
	}

	private static int func(int n, int from, int to) {
		int ret = 0;

		if (n == 1) {
			sb.append(from);
			sb.append(' ');
			sb.append(to);
			sb.append('\n');
			return 1;
		}

		int waypoint = 6 - from - to;
		ret += func(n - 1, from, waypoint);

		sb.append(from);
		sb.append(' ');
		sb.append(to);
		sb.append('\n');
		ret++;

		ret += func(n - 1, waypoint, to);

		return ret;
	}

}
