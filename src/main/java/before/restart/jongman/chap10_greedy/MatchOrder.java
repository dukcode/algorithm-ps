package before.restart.jongman.chap10_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class MatchOrder {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[] korean;
	private static int[] russian;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			russian = new int[n];

			st = new StringTokenizer(br.readLine());
			TreeSet<Integer> ratings = new TreeSet<>();
			for (int i = 0; i < n; ++i) {
				ratings.add(Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; ++i) {
				russian[i] = Integer.parseInt(st.nextToken());
			}

			int wins = 0;
			for (int rus = 0; rus < n; ++rus) {
				if (ratings.last() < russian[rus]) {
					ratings.remove(ratings.first());
					continue;
				}

				ratings.remove(ratings.ceiling(russian[rus]));
				wins++;
			}

			bw.write(String.valueOf(wins));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
