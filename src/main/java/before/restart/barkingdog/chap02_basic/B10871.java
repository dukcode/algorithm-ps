package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10871 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new FileReader("input.txt"));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			int num = Integer.parseInt(st.nextToken());
			if (num < x) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
		}

		br.close();
		bw.close();
	}

}
