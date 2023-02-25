package restart.barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B2164 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		q = IntStream.rangeClosed(1, n)
			.boxed()
			.collect(Collectors.toCollection(LinkedList::new));

		while (q.size() != 1) {
			q.poll();
			q.add(q.poll());
		}

		bw.write(String.valueOf(q.poll()));

		br.close();
		bw.close();
	}

}
