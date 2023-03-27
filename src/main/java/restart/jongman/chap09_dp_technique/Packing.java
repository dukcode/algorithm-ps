package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Packing {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int w;

	private static String[] items;
	private static int[] volumes;
	private static int[] needs;

	private static int[][] cache;    // y = item, x = volume, value = needs

	private static List<String> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			input();

			bw.write(String.valueOf(packing(w, 0)));
			bw.write(' ');
			reconstruct(w, 0);

			bw.write(String.valueOf(ans.size()));

			bw.newLine();
			for (String item : ans) {
				bw.write(String.valueOf(item));
				bw.newLine();
			}
		}

		br.close();
		bw.close();
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		items = new String[n];
		volumes = new int[n];
		needs = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			items[i] = st.nextToken();
			volumes[i] = Integer.parseInt(st.nextToken());
			needs[i] = Integer.parseInt(st.nextToken());
		}

		cache = new int[n][1001];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cache[i], -1);
		}
	}

	/**
	 * @param capacity 남은 용량
	 * @return item 부터 끝까지 짐을 쌋을 때 최대의 절박도
	 */
	private static int packing(int capacity, int item) {
		if (item == n) {
			return 0;
		}

		if (cache[item][capacity] != -1) {
			return cache[item][capacity];
		}

		cache[item][capacity] = packing(capacity, item + 1);

		if (capacity >= volumes[item]) {
			cache[item][capacity] = Math.max(cache[item][capacity],
				packing(capacity - volumes[item], item + 1) + needs[item]);
		}

		return cache[item][capacity];
	}

	private static void reconstruct(int capacity, int item) {
		if (item == n) {
			return;
		}

		if (packing(capacity, item) == packing(capacity, item + 1)) {
			reconstruct(capacity, item + 1);
		} else {
			ans.add(items[item]);
			reconstruct(capacity - volumes[item], item + 1);
		}
	}

}
