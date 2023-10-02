package before.restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Lis {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[] arr;

	private static int[] cache;
	private static int[] fromIdx;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		cache = new int[n + 1];
		fromIdx = new int[n + 1];

		lis(-1);
		System.out.println(Arrays.toString(cache));
		System.out.println(Arrays.toString(fromIdx));

		List<Integer> ans = reconstruct();
		for (Integer num : ans) {
			bw.write(String.valueOf(num));
			bw.write(' ');
		}
		bw.newLine();

		br.close();
		bw.close();
	}

	private static List<Integer> reconstruct() {
		List<Integer> ret = new ArrayList<>();

		int i = 0;
		while (fromIdx[i] != -1) {
			ret.add(arr[fromIdx[i]]);
			i = fromIdx[i] + 1;
		}

		return ret;
	}

	private static int lis(int idx) {
		if (idx != -1 && cache[idx + 1] != 0) {
			return cache[idx + 1];
		}

		cache[idx + 1] = 1;
		fromIdx[idx + 1] = -1;
		for (int next = idx + 1; next < n; next++) {
			if (idx == 5 && next == 7) {
				System.out.println("--");
			}
			if (idx == -1 || arr[idx] < arr[next]) {
				int length = lis(next);
				if (cache[idx + 1] < length + 1) {
					cache[idx + 1] = length + 1;
					fromIdx[idx + 1] = next;
				}
			}
		}

		return cache[idx + 1];
	}

}
