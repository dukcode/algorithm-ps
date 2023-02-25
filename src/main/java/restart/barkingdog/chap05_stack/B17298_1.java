package restart.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B17298_1 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int[] arr;
	private static int[] idxArr;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());

		arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		idxArr = new int[n];

		for (int i = n - 1; i >= 0; --i) {

			int idx = i + 1;
			while (idx < n && arr[i] >= arr[idx]) {
				idx = idxArr[idx];
			}

			idxArr[i] = idx;
		}

		for (int idx : idxArr) {
			bw.write(String.valueOf(idx == n ? -1 : arr[idx]));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
