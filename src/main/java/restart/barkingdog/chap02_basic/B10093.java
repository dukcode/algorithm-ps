package restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10093 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static long a;
	private static long b;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());

		if (a > b) {
			long tmp = a;
			a = b;
			b = tmp;
		}

		bw.write(String.valueOf(a == b ? 0 : b - a - 1));
		bw.newLine();

		for (long num = a + 1; num < b; ++num) {
			bw.write(String.valueOf(num));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
