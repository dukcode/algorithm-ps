package restart.barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1919 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int[] arr = new int[26];

	private static int ans;

	public static void main(String[] args) throws IOException {
		// 	br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str1 = br.readLine();
		String str2 = br.readLine();
		for (int i = 0; i < str1.length(); i++) {
			arr[str1.charAt(i) - 'a']++;
		}

		for (int i = 0; i < str2.length(); i++) {
			arr[str2.charAt(i) - 'a']--;
		}

		for (int i = 0; i < 26; i++) {
			ans += Math.abs(arr[i]);
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
