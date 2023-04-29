package restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BlockGame {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int c;
	private static int n = 5;
	private static int board;

	private static List<Integer> move = new ArrayList<>();

	private static int[] cache;

	static {
		preCalc();
		cache = new int[1 << (n * n)];
		Arrays.fill(cache, -1);
	}

	private static void preCalc() {
		for (int y = 0; y < n - 1; y++) {
			for (int x = 0; x < n - 1; x++) {
				List<Integer> cells = new ArrayList<>();
				for (int dy = 0; dy < 2; dy++) {
					for (int dx = 0; dx < 2; dx++) {
						cells.add(cell(y + dy, x + dx));
					}
				}

				int square = 0;
				for (Integer cell : cells) {
					square += cell;
				}

				for (Integer cell : cells) {
					move.add(square - cell);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				move.add(cell(i, j) + cell(i, j + 1)); // 가로
				move.add(cell(j, i) + cell(j + 1, i)); // 세로
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		c = Integer.parseInt(br.readLine());

		while (c-- > 0) {
			board = 0;
			for (int y = 0; y < n; y++) {
				char[] line = br.readLine().toCharArray();
				for (int x = 0; x < n; x++) {
					if (line[x] == '#') {
						board = board | cell(y, x);
					}
				}
			}

			bw.write(play(board) == 1 ? "WINNING" : "LOSING");
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static int play(int board) {
		if (board == (1 << (n * n))) {
			return 1;
		}

		if (cache[board] != -1) {
			return cache[board];
		}

		cache[board] = 0;
		for (Integer block : move) {
			if ((board & block) != 0) {
				continue;
			}

			if (play(board | block) == 0) {
				cache[board] = 1;
				break;
			}
		}

		return cache[board];
	}

	private static int cell(int y, int x) {
		return 1 << (y * n + x);
	}

}
