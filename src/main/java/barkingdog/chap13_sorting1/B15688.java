package barkingdog.chap13_sorting1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B15688 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static final int MAX = 1_000_000;

	private static int n;
	private static int[] freq = new int[2 * MAX + 1];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			freq[num + MAX]++;
		}

		for (int i = 0; i < 2 * MAX + 1; i++) {
			while (freq[i]-- > 0) {
				bw.write(String.valueOf(i - MAX));
				bw.newLine();
			}
		}

		br.close();
		bw.close();
	}

}
