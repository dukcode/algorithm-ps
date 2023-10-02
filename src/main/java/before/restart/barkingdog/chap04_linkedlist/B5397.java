package before.restart.barkingdog.chap04_linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class B5397 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static LinkedList<Character> list;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			String log = br.readLine();
			list = new LinkedList<>();
			ListIterator<Character> it = list.listIterator();
			for (int i = 0; i < log.length(); i++) {
				char c = log.charAt(i);

				switch (c) {
					case '<':
						if (it.hasPrevious()) {
							it.previous();
						}
						break;
					case '>':
						if (it.hasNext()) {
							it.next();
						}
						break;
					case '-':
						if (it.hasPrevious()) {
							it.previous();
							it.remove();
						}
						break;
					default:
						it.add(c);
						break;
				}
			}

			for (Character c : list) {
				bw.write(c);
			}
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
