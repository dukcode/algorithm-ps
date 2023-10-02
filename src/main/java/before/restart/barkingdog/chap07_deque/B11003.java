package before.restart.barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B11003 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int l;

	private static class Num {
		int num;
		int idx;

		public Num(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

	private static Deque<Num> dq = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			int num = Integer.parseInt(st.nextToken());

			while (!dq.isEmpty() && dq.peekLast().num >= num) {
				dq.pollLast();
			}

			if (!dq.isEmpty() && dq.peekFirst().idx < idx - l + 1) {
				dq.pollFirst();
			}

			dq.addLast(new Num(num, idx));

			bw.write(String.valueOf(dq.peekFirst().num));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
