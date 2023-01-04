package barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B1021 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static Deque<Integer> dq;

	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dq = IntStream.rangeClosed(1, n)
			.boxed()
			.collect(Collectors.toCollection(LinkedList::new));

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int goal = Integer.parseInt(st.nextToken());

			int cnt = 0;
			while (!dq.isEmpty() && dq.peekFirst() != goal) {
				cnt++;
				dq.addFirst(dq.pollLast());
			}

			cnt = Math.min(cnt, dq.size() - cnt);
			ans += cnt;

			dq.pollFirst();

		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
