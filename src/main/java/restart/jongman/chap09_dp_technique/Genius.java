package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Genius {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;

	private static int n;
	private static int k;
	private static int m;

	private static int[] songLength;
	private static double[][] prob;

	private static int[] like;

	private static double[][] cache = new double[k + 1][n];    // -1.0으로 초기화

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			songLength = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				songLength[i] = Integer.parseInt(st.nextToken());
			}

			prob = new double[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					prob[y][x] = Double.parseDouble(st.nextToken());
				}
			}

			like = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				like[i] = Integer.parseInt(st.nextToken());
			}

			cache = new double[k + 1][n];
			for (int i = 0; i <= k; ++i) {
				Arrays.fill(cache[i], -1.0);
			}

			for (int song : like) {
				double prob = 0.0;
				for (int minute = 0; minute < songLength[song]; ++minute) {
					prob += getProb(k - minute, song);
				}
				bw.write(String.format("%.8f", prob));
				bw.write(' ');
			}

			bw.newLine();
		}

		br.close();
		bw.close();
	}

	// time에 song이 재생을 시작할 확률
	private static double getProb(int time, int song) {
		if (time == 0) {
			return song == 0 ? 1.0 : 0.0;
		}

		if (cache[time][song] != -1.0) {
			return cache[time][song];
		}

		cache[time][song] = 0.0;
		for (int prev = 0; prev < n; ++prev) {
			if (time < songLength[prev]) {
				continue;
			}
			cache[time][song] += getProb(time - songLength[prev], prev) * prob[prev][song];
		}

		return cache[time][song];
	}

}
