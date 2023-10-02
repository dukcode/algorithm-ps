package before.restart.barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10808 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int[] freq = new int[26];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			freq[c - 'a']++;
		}

		for (int i = 0; i < 26; i++) {
			bw.write(String.valueOf(freq[i]));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
