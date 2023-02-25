package restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2562 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int max = Integer.MIN_VALUE;
	private static int idx;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 1; i <= 9; i++) {
			int num = Integer.parseInt(br.readLine());
			if (max < num) {
				max = num;
				idx = i;
			}
		}

		bw.write(String.valueOf(max));
		bw.newLine();
		bw.write(String.valueOf(idx));

		br.close();
		bw.close();
	}

}
