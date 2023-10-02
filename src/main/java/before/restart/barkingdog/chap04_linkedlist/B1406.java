package before.restart.barkingdog.chap04_linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class B1406 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;

	private static LinkedList<Character> list = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			list.add(c);
		}

		ListIterator<Character> it = list.listIterator(list.size());

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			switch (command) {
				case "L":
					if (it.hasPrevious()) {
						it.previous();
					}
					break;
				case "D":
					if (it.hasNext()) {
						it.next();
					}
					break;
				case "B":
					if (it.hasPrevious()) {
						it.previous();
						it.remove();
					}
					break;
				case "P":
					char c = st.nextToken().charAt(0);
					it.add(c);
					break;
				default:
					break;
			}
		}

		for (Character c : list) {
			bw.write(c);
		}

		br.close();
		bw.close();
	}

}
