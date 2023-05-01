package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Genius2 {
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

	private static double[][] w;

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

			w = new double[4 * n][4 * n];
			getProb();

			for (int song : like) {
				double prob = 0.0;
				for (int minute = 0; minute < songLength[song]; ++minute) {
					prob += w[(3 - minute) * n + song][3 * n];
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
		for (int y = 0; y < 4 * n; y++) {
			for (int x = 0; x < 4 * n; x++) {
				int dt = (y + n) / n - x / n;
				int prev = x % n;
				int now = y % n;
				if (dt == 0 && prev == now) {
					w[y][x] = 1.0;
					continue;
				}

				if (y >= 3 * n && dt == songLength[prev]) {
					w[y][x] = prob[prev][now];
				}
			}
		}

		// double[][] w2 = new double[4 * n][4 * n];
		// for (int i = 0; i < 3 * n; ++i) {
		// 	w2[i][i + n] = 1.0;
		// }
		// for (int now = 0; now < n; now++) {
		// 	for (int prev = 0; prev < n; prev++) {
		// 		w2[3 * n + now][(4 - songLength[prev]) * n + prev] = prob[prev][now];
		// 	}
		// }

		// print(w);
		// print(w2);
		w = pow(w, k);

	}

	private static void print(double[][] array) {
		for (int y = 0; y < 4 * n; y++) {
			for (int x = 0; x < 4 * n; x++) {
				System.out.printf("%.2f ", array[y][x]);
				if (x % n == n - 1) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if (y % n == n - 1) {
				System.out.println();
			}
		}
		System.out.println();
	}

	private static double[][] pow(double[][] data, long b) {

		int n = data.length;

		if (b == 0) {
			double[][] tmp = new double[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tmp[i][j] = 1.0;
				}
			}
			return tmp;
		}
		if (b == 1)
			return data;
		if (b % 2 == 1) {    //홀수
			double[][] tmp = pow(data, b - 1);
			return countPow(data, tmp);
		} else {//짝수
			double[][] tmp = pow(data, b / 2);
			return countPow(tmp, tmp);
		}
	}

	static double[][] countPow(double[][] dataA, double[][] dataB) {
		int n = dataA.length;
		double[][] result = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				double tmp = 0;
				for (int k = 0; k < n; k++) {
					tmp += dataA[i][k] * dataB[k][j];
				}
				result[i][j] = tmp;
			}
		}
		return result;
	}
}
