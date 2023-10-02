package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B2309 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int[] dwarfs = new int[9];
	private static int sum;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 9; i++) {
			int height = Integer.parseInt(br.readLine());
			dwarfs[i] = height;
			sum += height;
		}

		Arrays.sort(dwarfs);

		Loop:
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - dwarfs[i] - dwarfs[j] == 100) {
					for (int k = 0; k < 9; k++) {
						if (k == i || k == j) {
							continue;
						}

						bw.write(String.valueOf(dwarfs[k]));
						bw.newLine();
					}
					break Loop;
				}
			}
		}

		br.close();
		bw.close();
	}

}
