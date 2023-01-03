package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B3273 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int x;
	private static int[] cntArr = new int[1000001];

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			int counter = x - num;

			if (counter < 0 || counter > 1000000) {
				continue;
			}

			ans += cntArr[counter];
			cntArr[num]++;
		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
