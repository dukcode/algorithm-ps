package before.restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Genius1 {
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

	private static double[][] cache;

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

			cache = new double[5][n];
			getProb();

			for (int song : like) {
				double prob = 0.0;
				for (int minute = 0; minute < songLength[song]; ++minute) {
					prob += cache[(5 + k - minute) % 5][song];
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
	private static void getProb() {
		cache[0][0] = 1.0;
		for (int time = 1; time <= k; ++time) {
			for (int song = 0; song < n; ++song) {
				cache[time % 5][song] = 0.0;
				for (int prev = 0; prev < n; ++prev) {
					cache[time % 5][song] += cache[(5 + time - songLength[prev]) % 5][prev] * prob[prev][song];
				}
			}
		}

	}
}
