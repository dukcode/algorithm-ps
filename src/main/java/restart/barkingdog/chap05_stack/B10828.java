package restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10828 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static int[] data = new int[10000];
	private static int pos = 0;

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
					data[pos++] = num;
					break;
				case "pop":
					if (pos == 0) {
						bw.write("-1");
					} else {
						bw.write(String.valueOf(data[--pos]));
					}
					bw.newLine();
					break;
				case "size":
					bw.write(String.valueOf(pos));
					bw.newLine();
					break;
				case "empty":
					if (pos == 0) {
						bw.write('1');
					} else {
						bw.write('0');
					}
					bw.newLine();
					break;
				case "top":
					if (pos == 0) {
						bw.write("-1");
					} else {
						bw.write(String.valueOf(data[pos - 1]));
					}
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
