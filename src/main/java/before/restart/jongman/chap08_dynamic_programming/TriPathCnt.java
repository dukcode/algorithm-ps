package before.restart.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TriPathCnt {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int[][] triangle;

	private static int[][] solutionCache;
	private static int[][] countCache;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());

			triangle = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x <= y; x++) {
					triangle[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			solutionCache = new int[n][n];
			countCache = new int[n][n];

			solution(0, 0);
			int ans = count(0, 0);

			bw.write(String.valueOf(ans));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int count(int y, int x) {
		if (y == n - 1) {
			return 1;
		}

		if (countCache[y][x] != 0) {
			return countCache[y][x];
		}

		countCache[y][x] += solutionCache[y + 1][x] >= solutionCache[y + 1][x + 1] ? count(y + 1, x) : 0;
		countCache[y][x] += solutionCache[y + 1][x] <= solutionCache[y + 1][x + 1] ? count(y + 1, x + 1) : 0;

		return countCache[y][x];
	}

	private static int solution(int y, int x) {
		if (y == n) {
			return 0;
		}

		if (solutionCache[y][x] != 0) {
			return solutionCache[y][x];
		}

		return solutionCache[y][x] = triangle[y][x] + Math.max(solution(y + 1, x), solution(y + 1, x + 1));
	}

}
