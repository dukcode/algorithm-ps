package practice.jongman.brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Picnic {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int m;
	private static boolean[][] friends;
	private static boolean[] taken;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			taken = new boolean[n];
			friends = new boolean[n][n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; ++i) {
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());

				friends[first][second] = true;
				friends[second][first] = true;
			}

			bw.write(String.valueOf(count()));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int count() {

		int first = -1;
		for (int i = 0; i < n; ++i) {
			if (!taken[i]) {
				first = i;
				break;
			}
		}

		if (first == -1) {
			return 1;
		}

		int ret = 0;
		for (int second = first + 1; second < n; ++second) {
			if (taken[second] || !friends[first][second]) {
				continue;
			}

			taken[first] = true;
			taken[second] = true;
			ret += count();
			taken[first] = false;
			taken[second] = false;
		}

		return ret;
	}

}
