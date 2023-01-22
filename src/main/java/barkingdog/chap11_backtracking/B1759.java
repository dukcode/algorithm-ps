package barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int l;
	private static int c;

	private static char[] arr;
	private static char[] ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; ++i) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);

		ans = new char[l];
		func(0, 0, 0);

		br.close();
		bw.close();
	}

	private static void func(int idx, int startIdx, int countVowel) throws IOException {
		if (idx == l) {
			if (countVowel < 1 || l - countVowel < 2) {
				return;
			}

			for (int i = 0; i < l; ++i) {
				bw.write(ans[i]);
			}
			bw.newLine();
			return;
		}

		for (int i = startIdx; i < c; ++i) {
			ans[idx] = arr[i];
			func(idx + 1, i + 1, countVowel + (isVowel(arr[i]) ? 1 : 0));
		}
	}

	private static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

}
