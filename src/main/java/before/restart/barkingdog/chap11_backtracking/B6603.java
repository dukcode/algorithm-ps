package before.restart.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B6603 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int k;
	private static int[] s;

	private static int[] ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());

			if (k == 0) {
				break;
			}

			s = new int[k];
			for (int i = 0; i < k; ++i) {
				s[i] = Integer.parseInt(st.nextToken());
			}

			ans = new int[6];
			func(0, 0);
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void func(int idx, int startIdx) throws IOException {
		if (idx == 6) {
			for (int num : ans) {
				bw.write(String.valueOf(num));
				bw.write(' ');
			}
			bw.newLine();
			return;
		}

		for (int i = startIdx; i < k; ++i) {
			ans[idx] = s[i];
			func(idx + 1, i + 1);
		}
	}

}
