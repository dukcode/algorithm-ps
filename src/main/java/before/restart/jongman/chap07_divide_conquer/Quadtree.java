package before.restart.jongman.chap07_divide_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.StringCharacterIterator;

public class Quadtree {

	private static BufferedReader br;
	private static BufferedWriter bw;

	private final static int MX = 1 << 4;
	private static char[][] decompressed;
	private static int c;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());
		while (c-- > 0) {
			decompressed = new char[MX][MX];
			String str = br.readLine();
			StringCharacterIterator it = new StringCharacterIterator(str);

			bw.write(reverse(it));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static String reverse(StringCharacterIterator it) {
		char head = it.current();
		it.next();

		if (head == 'b' || head == 'w') {
			return String.valueOf(head);
		}

		String upperLeft = reverse(it);
		String upperRight = reverse(it);
		String lowerLeft = reverse(it);
		String lowerRight = reverse(it);

		return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
	}

	private static void decompress(StringCharacterIterator it, int y, int x, int size) {
		char head = it.current();
		it.next();

		if (head == 'w' || head == 'b') {
			for (int dy = 0; dy < size; dy++) {
				for (int dx = 0; dx < size; dx++) {
					int ny = y + dy;
					int nx = x + dx;

					decompressed[ny][nx] = head;
				}
			}

			return;
		}

		int half = size / 2;
		decompress(it, y, x, half);
		decompress(it, y, x + half, half);
		decompress(it, y + half, x, half);
		decompress(it, y + half, x + half, half);
	}

}
