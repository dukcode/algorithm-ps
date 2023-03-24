package restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Snail {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int m;
	private static double[][] cache;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			cache = new double[m][2 * n + 1];
			for (int i = 0; i < m; i++) {
				Arrays.fill(cache[i], -1.0);
			}

			bw.write(String.valueOf(probability(0, 0)));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static double probability(int day, int dist) {
		if (day == m) {
			return dist >= n ? 1.0 : 0.0;
		}

		if (cache[day][dist] != -1.0) {
			return cache[day][dist];
		}

		return cache[day][dist] = 0.25 * probability(day + 1, dist + 1) + 0.75 * probability(day + 1, dist + 2);
	}

}
