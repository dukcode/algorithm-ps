package restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1267 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static int ySum;
	private static int mSum;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int t = Integer.parseInt(st.nextToken());

			ySum += (t / 30 + 1) * 10;
			mSum += (t / 60 + 1) * 15;
		}

		if (ySum < mSum) {
			bw.write("Y ");
			bw.write(String.valueOf(ySum));
		} else if (ySum == mSum) {
			bw.write("Y M ");
			bw.write(String.valueOf(ySum));
		} else {
			bw.write("M ");
			bw.write(String.valueOf(mSum));
		}

		br.close();
		bw.close();
	}

}
