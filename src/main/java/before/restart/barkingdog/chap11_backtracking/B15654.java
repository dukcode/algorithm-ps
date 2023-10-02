package before.restart.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15654 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;

	private static int[] arr;
	private static boolean[] taken;

	private static int[] ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		taken = new boolean[n];

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		ans = new int[m];
		func(0);

		br.close();
		bw.close();
	}

	private static void func(int idx) throws IOException {
		if (idx == m) {
			for (int num : ans) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
			bw.newLine();
			return;
		}

		for (int i = 0; i < n; ++i) {
			if (taken[i]) {
				continue;
			}

			taken[i] = true;
			ans[idx] = arr[i];
			func(idx + 1);
			taken[i] = false;
		}
	}

}
