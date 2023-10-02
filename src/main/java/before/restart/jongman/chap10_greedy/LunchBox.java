package before.restart.jongman.chap10_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LunchBox {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static class Bento {
		int heatTime;
		int eatTime;

		public Bento(int heatTime, int eatTime) {
			this.heatTime = heatTime;
			this.eatTime = eatTime;
		}
	}

	private static int c;
	private static int n;
	private static int[] m;
	private static int[] e;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			Bento[] bentos = new Bento[n];

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; ++i) {
				int heatTime = Integer.parseInt(st1.nextToken());
				int eatTime = Integer.parseInt(st2.nextToken());
				bentos[i] = new Bento(heatTime, eatTime);
			}

			Arrays.sort(bentos, (b1, b2) -> b2.eatTime - b1.eatTime);

			int ret = 0;
			int sumHeatTime = 0;
			for (int i = 0; i < n; ++i) {
				sumHeatTime += bentos[i].heatTime;
				ret = Math.max(ret, sumHeatTime + bentos[i].eatTime);
			}

			bw.write(String.valueOf(ret));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
