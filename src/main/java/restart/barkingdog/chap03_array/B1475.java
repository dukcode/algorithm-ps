package restart.barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B1475 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static String n;

	private static int[] freq = new int[10];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = br.readLine();

		for (int i = 0; i < n.length(); i++) {
			char c = n.charAt(i);
			if (c == '6') {
				freq[9]++;
				continue;
			}

			freq[c - '0']++;
		}

		freq[9] = (freq[9] + 1) / 2;

		bw.write(String.valueOf(Arrays.stream(freq).max().getAsInt()));

		br.close();
		bw.close();
	}

}
