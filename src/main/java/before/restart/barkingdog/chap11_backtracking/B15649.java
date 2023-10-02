package before.restart.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B15649 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;
	private static int[] arr;
	private static boolean[] taken;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[m];
		taken = new boolean[n + 1];

		func(0);

		br.close();
		bw.close();
	}

	private static void func(int idx) throws IOException {
		if (idx == m) {
			for (int num : arr) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
			bw.newLine();
			return;
		}

		for (int num = 1; num <= n; ++num) {
			if (taken[num]) {
				continue;
			}

			taken[num] = true;
			arr[idx] = num;
			func(idx + 1);
			taken[num] = false;
		}

	}

}
