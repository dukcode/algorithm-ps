package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15552 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static int a;
	private static int b;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			bw.write(String.valueOf(a + b));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
