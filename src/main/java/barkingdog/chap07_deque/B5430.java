package barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class B5430 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int t;
	private static String p;
	private static int n;

	private static LinkedList<Integer> dq;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		t = Integer.parseInt(br.readLine());

		Loop:
		while (t-- > 0) {
			p = br.readLine();
			n = Integer.parseInt(br.readLine());

			String line = br.readLine();
			st = new StringTokenizer(line.substring(1, line.length() - 1), ",");

			dq = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				dq.addLast(num);
			}

			boolean isFlipped = false;
			for (int i = 0; i < p.length(); i++) {
				char c = p.charAt(i);

				switch (c) {
					case 'R':
						isFlipped = !isFlipped;
						break;
					case 'D':
						if (dq.isEmpty()) {
							bw.write("error");
							bw.newLine();
							continue Loop;
						}

						if (isFlipped) {
							dq.pollLast();
						} else {
							dq.pollFirst();
						}
						break;
					default:
						break;
				}
			}

			StringJoiner sj = new StringJoiner(",", "[", "]");
			Iterator<Integer> it = isFlipped ? dq.descendingIterator() : dq.listIterator();
			while (it.hasNext()) {
				sj.add(String.valueOf(it.next()));
			}

			bw.write(sj.toString());
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
