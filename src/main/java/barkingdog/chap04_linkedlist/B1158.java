package barkingdog.chap04_linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B1158 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static LinkedList<Integer> list;
	private static StringJoiner sj = new StringJoiner(", ", "<", ">");

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		list = IntStream.rangeClosed(1, n)
			.boxed()
			.collect(Collectors.toCollection(LinkedList::new));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k - 1; j++) {
				list.addLast(list.pollFirst());
			}
			sj.add(String.valueOf(list.pollFirst()));
		}

		bw.write(sj.toString());

		br.close();
		bw.close();
	}

}
