package restart.barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10845 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static int[] data = new int[10005];
	private static int head = 0;
	private static int tail = 0;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();

			switch (c) {
				case "push":
					int num = Integer.parseInt(st.nextToken());
					data[tail++] = num;
					break;
				case "pop":
					bw.write(String.valueOf(tail - head == 0 ? -1 : data[head++]));
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
