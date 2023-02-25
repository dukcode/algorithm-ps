package restart.barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class B10804 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int[] cards;

	private static int s;
	private static int e;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		cards = IntStream.rangeClosed(1, 20).toArray();

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()) - 1;
			e = Integer.parseInt(st.nextToken()) - 1;

			flip(s, e);
		}

		for (int num : cards) {
			bw.write(String.valueOf(num));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

	private static void flip(int s, int e) {
		while (s < e) {
			int tmp = cards[s];
			cards[s] = cards[e];
			cards[e] = tmp;
			s++;
			e--;
		}
	}

}
