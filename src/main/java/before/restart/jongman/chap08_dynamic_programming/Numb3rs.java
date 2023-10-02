package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Numb3rs {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;

	private static int n;    // 마을의 수
	private static int d;    // 지난 일 수
	private static int p;    // 교도소가 있는 마을의 번호
	private static int[][] route;
	private static int t;

	private static int[] q;

	private static double[][] cache;
	private static int[] numRoute;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			input();

			for (int i = 0; i < t; i++) {
				bw.write(String.format("%.8f", calculateProbability(d, q[i])));
				bw.write(' ');
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		route = new int[n][n];
		numRoute = new int[n];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			int sumRoute = 0;

			for (int x = 0; x < n; x++) {
				int isConnected = Integer.parseInt(st.nextToken());
				route[y][x] = isConnected;
				sumRoute += isConnected;
			}

			numRoute[y] = sumRoute;
		}

		t = Integer.parseInt(br.readLine());

		q = new int[t];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < t; i++) {
			q[i] = Integer.parseInt(st.nextToken());
		}

		cache = new double[d + 1][n];
	}

	private static double calculateProbability(int day, int village) {
		if (day == 0) {
			if (village == p) {
				return 1.0;
			}

			return 0.0;
		}

		if (cache[day][village] != 0.0) {
			return cache[day][village];
		}

		for (int before = 0; before < n; before++) {
			if (route[village][before] == 0) {
				continue;
			}

			cache[day][village] += calculateProbability(day - 1, before) / numRoute[before];
		}

		return cache[day][village];
	}

}
