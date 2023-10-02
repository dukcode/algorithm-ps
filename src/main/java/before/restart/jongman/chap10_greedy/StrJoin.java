package before.restart.jongman.chap10_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class StrJoin {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			pq = new PriorityQueue<>();
			for (int i = 0; i < n; ++i) {
				pq.add(Integer.parseInt(st.nextToken()));
			}

			int sum = 0;
			while (pq.size() > 1) {
				int min1 = pq.poll();
				int min2 = pq.poll();
				pq.offer(min1 + min2);
				sum += min1 + min2;
			}

			bw.write(String.valueOf(sum));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
