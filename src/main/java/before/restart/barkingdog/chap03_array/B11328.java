package before.restart.barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11328 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int[] arr = new int[26];

	private static int n;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		Loop:
		for (int i = 0; i < n; i++) {
			Arrays.fill(arr, 0);

			st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			for (int j = 0; j < str1.length(); j++) {
				arr[str1.charAt(j) - 'a']++;
			}
			String str2 = st.nextToken();
			for (int j = 0; j < str2.length(); j++) {
				arr[str2.charAt(j) - 'a']--;
			}

			for (int j = 0; j < 26; j++) {
				if (arr[j] != 0) {
					bw.write("Impossible");
					bw.newLine();
					continue Loop;
				}
			}

			bw.write("Possible");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
