package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9498 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int score;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new FileReader("input.txt"));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		score = Integer.parseInt(br.readLine());
		char ans = 'F';

		if (score >= 90) {
			ans = 'A';
		} else if (score >= 80) {
			ans = 'B';
		} else if (score >= 70) {
			ans = 'C';
		} else if (score >= 60) {
			ans = 'D';
		}

		bw.write(ans);

		br.close();
		bw.close();
	}

}
