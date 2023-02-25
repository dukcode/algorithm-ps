package restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2576 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int sum = 0;
	private static int minOdd = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 7; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num % 2 == 0) {
				continue;
			}

			sum += num;
			minOdd = Math.min(minOdd, num);
		}

		if (minOdd == Integer.MAX_VALUE) {
			bw.write(String.valueOf(-1));
		} else {
			bw.write(String.valueOf(sum));
			bw.newLine();
			bw.write(String.valueOf(minOdd));
		}

		br.close();
		bw.close();
	}

}
