package before.restart.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9663 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static int ans;

	private static boolean[] takenVertical;
	private static boolean[] takenRightDown;    // y - x 일정
	private static boolean[] takenLeftDown;        // y + x 일정

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		takenVertical = new boolean[n];
		takenRightDown = new boolean[2 * n - 1];
		takenLeftDown = new boolean[2 * n - 1];

		func(0);

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static void func(int row) {
		if (row == n) {
			ans++;
			return;
		}

		for (int col = 0; col < n; ++col) {
			if (takenVertical[col]
				|| takenRightDown[col - row + (n - 1)]
				|| takenLeftDown[col + row]) {
				continue;
			}

			takenVertical[col] = true;
			takenRightDown[col - row + (n - 1)] = true;
			takenLeftDown[col + row] = true;

			func(row + 1);

			takenVertical[col] = false;
			takenRightDown[col - row + (n - 1)] = false;
			takenLeftDown[col + row] = false;
		}

	}

}
