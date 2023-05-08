package restart.jongman.chap10_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Minastirith {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static final int MX = 987_654_321;

	private static int c;
	private static int n;

	private static double[] y;
	private static double[] x;
	private static double[] r;

	private static class Range {
		double st;
		double en;

		public Range(double st, double en) {
			this.st = st;
			this.en = en;
		}
	}

	private static Range[] ranges;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			ranges = new Range[n];
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				double y = Double.parseDouble(st.nextToken());
				double x = Double.parseDouble(st.nextToken());
				double r = Double.parseDouble(st.nextToken());

				double loc = (2 * Math.PI + Math.atan2(x, y)) % (2 * Math.PI);
				double range = 2 * Math.asin(r / 16.0);

				ranges[i] = new Range(loc - range, loc + range);
			}

			Arrays.sort(ranges, Comparator.comparingDouble(r -> r.st));

			for (Range r : ranges) {
				System.out.println(r.st + " " + r.en);
			}
			System.out.println();

			int ans = solveCircular();
			bw.write(ans >= MX ? "IMPOSSIBLE" : String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int solveCircular() {
		int ret = MX;
		for (int i = 0; i < n; ++i) {
			Range range = ranges[i];
			if (range.st <= 0 || range.en >= 2 * Math.PI) {
				double st = range.en % (2 * Math.PI);
				double en = (range.st + 2 * Math.PI) % (2 * Math.PI);

				ret = Math.min(ret, 1 + solveLinear(st, en));
			}
		}
		return ret;
	}

	private static int solveLinear(double st, double en) {
		int used = 0;
		int idx = 0;
		while (st < en) {
			double maxCover = -1;
			while (idx < n && ranges[idx].st <= st) {
				maxCover = Math.max(maxCover, ranges[idx].en);
				++idx;
			}

			if (maxCover <= st) {
				return MX;
			}
			st = maxCover;
			++used;
		}

		return used;
	}

}
