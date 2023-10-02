package before.restart.jongman.chap18_linear_data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Josephus {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n;
	private static int k;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			josephus(n, k);
		}

		br.close();
		bw.close();
	}

	private static void josephus(int n, int k) throws IOException {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= n; ++i) {
			list.add(i);
		}

		ListIterator<Integer> it = list.listIterator();
		while (n > 2) {
			if (!it.hasNext()) {
				it = list.listIterator();
			}
			it.next();
			it.remove();
			--n;

			for (int i = 0; i < k - 1; ++i) {
				if (!it.hasNext()) {
					it = list.listIterator();
				}

				it.next();
			}
		}

		for (int num : list) {
			bw.write(String.valueOf(num));
			bw.write(' ');
		}
		bw.newLine();
	}

}
