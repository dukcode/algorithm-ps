package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15657 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;

	private static int[] arr;

	private static int[] ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		ans = new int[m];
		func(0, 0);

		br.close();
		bw.close();
	}

	private static void func(int idxToFill, int lastIdx) throws IOException {
		if (idxToFill == m) {
			for (int num : ans) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
			bw.newLine();
			return;
		}

		for (int i = lastIdx; i < n; ++i) {
			ans[idxToFill] = arr[i];
			func(idxToFill + 1, i);
		}
	}

}
