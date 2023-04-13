package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Restore {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int c;
	private static int k;

	private static String[] words;
	private static boolean[] removed;

	private static int[][] overlaps;
	private static int[][] cache;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			k = Integer.parseInt(br.readLine());
			words = new String[k];
			for (int i = 0; i < k; i++) {
				words[i] = br.readLine();
			}

			removed = new boolean[k];
			for (int first = 0; first < k; first++) {
				for (int second = 0; second < k; second++) {
					if (first == second || removed[first] || removed[second]) {
						continue;
					}

					if (words[second].contains(words[first])) {
						removed[first] = true;
						break;
					}
				}
			}

			words = IntStream.range(0, k)
				.filter(i -> !removed[i])
				.mapToObj(i -> words[i])
				.toArray(String[]::new);
			k = words.length;
			overlaps = new int[k + 1][k];
			preCalcOverlaps();

			cache = new int[k + 1][(1 << k)];
			for (int i = 0; i <= k; i++) {
				Arrays.fill(cache[i], -1);
			}

			bw.write(reconstruct(k, 0));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static String reconstruct(int last, int used) {
		if (used == (1 << k) - 1) {
			return "";
		}

		for (int next = 0; next < k; next++) {
			if ((used & (1 << next)) != 0) {
				continue;
			}

			int ifUsed = overlaps[last][next] + restore(next, used + (1 << next));
			if (ifUsed == restore(last, used)) {
				return words[next].substring(overlaps[last][next]) + reconstruct(next, used + (1 << next));
			}
		}

		return "***oops***";
	}

	private static int restore(int last, int used) {
		if (used == ((1 << k) - 1)) {
			return 0;
		}

		if (cache[last][used] != -1) {
			return cache[last][used];
		}

		cache[last][used] = 0;
		for (int next = 0; next < k; next++) {
			if ((used & (1 << next)) != 0) {
				continue;
			}

			int candidate = overlaps[last][next] + restore(next, used + (1 << next));
			cache[last][used] = Math.max(cache[last][used], candidate);
		}

		return cache[last][used];
	}

	private static void preCalcOverlaps() {
		for (int y = 0; y < k; y++) {
			for (int x = 0; x < k; x++) {
				if (y == x) {
					continue;
				}

				int start = words[y].length() > words[x].length() ?
					words[y].length() - words[x].length() : 0;
				int cnt = 0;
				for (int i = start; i < words[y].length(); i++) {
					if (words[x].startsWith(words[y].substring(i))) {
						cnt = words[y].length() - i;
						break;
					}

				}
				overlaps[y][x] = cnt;
			}
		}
	}
}
