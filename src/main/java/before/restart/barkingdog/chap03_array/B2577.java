package before.restart.barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2577 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int a;
	private static int b;
	private static int c;

	private static int[] freq = new int[10];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		a = Integer.parseInt(br.readLine());
		b = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());

		String sum = String.valueOf(a * b * c);

		for (int i = 0; i < sum.length(); i++) {
			int c = sum.charAt(i);

			freq[c - '0']++;

		}

		for (int i = 0; i < 10; i++) {
			bw.write(String.valueOf(freq[i]));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
