package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Dragon {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;

	private static int n;
	private static int p;
	private static int l;

	private static String dragon0thGen = "FX";

	private static String expandX = "X+YF";
	private static String expandY = "FX-Y";

	private static int[] length = new int[51];

	private static final int MX = 1_000_000_000 + 1;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		preCalc();

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= l; i++) {
				bw.write(curve(dragon0thGen, n, (p - 1) + (i - 1)));
			}

		}

		br.close();
		bw.close();
	}

	private static void preCalc() {
		length[0] = 1;
		for (int i = 1; i <= 50; i++) {
			length[i] = Math.min(MX, length[i - 1] * 2 + 2);
		}
	}

	private static char curve(String seed, int gen, int skip) {
		if (gen == 0) {
			return seed.charAt(skip);
		}

		for (int idx = 0; idx < seed.length(); idx++) {
			if (seed.charAt(idx) == 'X' || seed.charAt(idx) == 'Y') {

				if (skip >= length[gen]) {
					skip -= length[gen];
				} else if (seed.charAt(idx) == 'X') {
					return curve(expandX, gen - 1, skip);
				} else {
					return curve(expandY, gen - 1, skip);
				}
			} else if (skip > 0) {
				skip--;
			} else {
				return seed.charAt(skip);
			}
		}

		return '#';
	}

}
