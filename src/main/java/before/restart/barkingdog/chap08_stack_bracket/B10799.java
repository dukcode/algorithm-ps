package before.restart.barkingdog.chap08_stack_bracket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10799 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int size;
	private static int ans;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = br.readLine();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c == '(') {
				size++;
				continue;
			}

			size--;
			if (line.charAt(i - 1) == '(') {
				ans += size;
			} else {
				ans++;
			}

		}

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

}
