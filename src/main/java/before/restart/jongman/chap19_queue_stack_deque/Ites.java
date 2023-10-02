package before.restart.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ites {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int k;
	private static int n;

	private static class SignGenerator {
		long seed = 1983;

		public long next() {
			long ret = seed;
			seed = (seed * 214013L + 2531011L) % (1L << 32);
			return (ret % 10000L + 1L);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			bw.write(String.valueOf(count()));
			bw.newLine();

		}

		br.close();
		bw.close();
	}

	private static long count() {
		int count = 0;

		SignGenerator generator = new SignGenerator();
		Queue<Long> queue = new LinkedList<>();

		long sum = 0;
		for (int i = 0; i < n; ++i) {
			long signal = generator.next();
			queue.offer(signal);
			sum += signal;
			while (sum > k) {
				sum -= queue.poll();
			}

			if (sum == k) {
				count++;
			}
		}

		return count;
	}

}
