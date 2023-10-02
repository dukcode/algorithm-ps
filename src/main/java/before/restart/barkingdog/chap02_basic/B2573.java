package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2573 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int y;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		y = Integer.parseInt(br.readLine());

		if (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) {
			bw.write('1');
		} else {
			bw.write('0');
		}

		br.close();
		bw.close();
	}

}
