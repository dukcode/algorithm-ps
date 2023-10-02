package before.restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2490 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 3; i++) {
			int sum = Arrays.stream(br.readLine().split(" "))
				.map(Integer::parseInt)
				.reduce(0, Integer::sum);

			switch (sum) {
				case 0:        // 웇
					bw.write('D');
					break;
				case 1:        // 도
					bw.write('C');
					break;
				case 2:        // 개
					bw.write('B');
					break;
				case 3:        // 걸
					bw.write('A');
					break;
				case 4:        // 모
					bw.write('E');
					break;
				default:
					break;
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
