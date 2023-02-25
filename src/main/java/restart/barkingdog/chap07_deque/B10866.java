package restart.barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10866 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static final int MX = 10005;
	private static int[] data = new int[2 * MX + 1];
	private static int head = MX;
	private static int tail = MX;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());

			String c = st.nextToken();

			switch (c) {
				case "push_front":
					int numFront = Integer.parseInt(st.nextToken());
					data[--head] = numFront;
					break;
				case "push_back":
					int numBack = Integer.parseInt(st.nextToken());
					data[tail++] = numBack;
					break;
				case "pop_front":
					bw.write(String.valueOf(tail - head == 0 ? -1 : data[head++]));
					bw.newLine();
					break;
				case "pop_back":
					bw.write(String.valueOf(tail - head == 0 ? -1 : data[--tail]));
					bw.newLine();
					break;
				case "size":
					bw.write(String.valueOf(tail - head));
					bw.newLine();
					break;
				case "empty":
					bw.write(String.valueOf(tail - head == 0 ? 1 : 0));
					bw.newLine();
					break;
				case "front":
					bw.write(String.valueOf(tail - head == 0 ? -1 : data[head]));
					bw.newLine();
					break;
				case "back":
					bw.write(String.valueOf(tail - head == 0 ? -1 : data[tail - 1]));
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
