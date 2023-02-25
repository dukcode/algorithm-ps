package restart.barkingdog.chap13_sorting1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10989 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static int[] freq = new int[10001];

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			freq[num]++;
		}

		for (int i = 1; i <= 10000; i++) {
			while (freq[i]-- > 0) {
				bw.write(String.valueOf(i));
				bw.newLine();
			}
		}

		br.close();
		bw.close();
	}

}
