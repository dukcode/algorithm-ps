package before.restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Klis {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int k;
	private static int[] arr;

	private static int[] cacheLen;
	private static int[] cacheCnt;

	private static final int MX = Integer.MAX_VALUE;

	private static List<Integer> ans;

	private static class Num {
		int value;
		int idx;

		public Num(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[n];
			cacheLen = new int[n + 1];
			cacheCnt = new int[n + 1];
			Arrays.fill(cacheLen, -1);
			Arrays.fill(cacheCnt, -1);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int length = lis(-1) - 1;
			count(-1);

			ans = new ArrayList<>();
			reconstruct(-1, k - 1);

			bw.write(String.valueOf(length));
			bw.newLine();
			for (Integer num : ans) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
			bw.newLine();

		}

		br.close();
		bw.close();
	}

	private static void reconstruct(int start, int skip) {
		if (start != -1) {
			ans.add(arr[start]);
		}

		List<Num> followers = new ArrayList<>();
		for (int next = start + 1; next < n; next++) {
			if ((start == -1 || arr[start] < arr[next]) && lis(start) == lis(next) + 1) {
				followers.add(new Num(arr[next], next));
			}
		}

		followers.sort((n1, n2) -> n1.value - n2.value);
		for (Num follower : followers) {
			int idx = follower.idx;
			int cnt = count(idx);

			if (cnt <= skip) {
				skip -= cnt;
			} else {
				reconstruct(idx, skip);
				break;
			}
		}
	}

	private static int lis(int idx) {
		if (cacheLen[idx + 1] != -1) {
			return cacheLen[idx + 1];
		}

		cacheLen[idx + 1] = 1;
		for (int next = idx + 1; next < n; next++) {
			if (idx != -1 && arr[idx] >= arr[next]) {
				continue;
			}

			cacheLen[idx + 1] = Math.max(cacheLen[idx + 1], lis(next) + 1);
		}

		return cacheLen[idx + 1];
	}

	private static int count(int idx) {
		if (lis(idx) == 1) {
			return 1;
		}

		if (cacheCnt[idx + 1] != -1) {
			return cacheCnt[idx + 1];
		}

		cacheCnt[idx + 1] = 0;
		for (int next = idx + 1; next < n; next++) {

			if ((idx == -1 || arr[idx] < arr[next]) && lis(idx) == lis(next) + 1) {
				cacheCnt[idx + 1] = (int)Math.min(MX, (long)cacheCnt[idx + 1] + count(next));
			}

		}

		return cacheCnt[idx + 1];
	}

}
