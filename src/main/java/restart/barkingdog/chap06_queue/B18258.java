package restart.barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18258 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		int num = -1;
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();

			switch (c) {
				case "push":
					num = Integer.parseInt(st.nextToken());
					q.add(num);
					break;
				case "pop":
					bw.write(String.valueOf(q.isEmpty() ? -1 : q.poll()));
					bw.newLine();
					break;
				case "size":
					bw.write(String.valueOf(q.size()));
					bw.newLine();
					break;
				case "empty":
					bw.write(String.valueOf(q.isEmpty() ? 1 : 0));
					bw.newLine();
					break;
				case "front":
					bw.write(String.valueOf(q.isEmpty() ? -1 : q.peek()));
					bw.newLine();
					break;
				case "back":
					bw.write(String.valueOf(q.isEmpty() ? -1 : num));
					bw.newLine();
					break;
				default:
					break;
			}
		}

		br.close();
		bw.close();
	}

}
