package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2480 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int[] dice = new int[7];

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			dice[num]++;
		}

		for (int num = 1; num <= 6; num++) {
			if (dice[num] == 3) {
				ans = 10000 + num * 1000;
				break;
			}

			if (dice[num] == 2) {
				ans = 1000 + num * 100;
				break;
			}

			if (dice[num] == 1) {
				ans = num * 100;
			}
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
