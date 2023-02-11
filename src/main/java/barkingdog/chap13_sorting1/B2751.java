package barkingdog.chap13_sorting1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B2751 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		mergeSort(0, n);

		for (int i = 0; i < n; i++) {
			bw.write(String.valueOf(arr[i]));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void mergeSort(int st, int en) {
		if (st + 1 == en) {
			return;
		}

		int mid = (st + en) / 2;
		mergeSort(st, mid);
		mergeSort(mid, en);
		merge(st, en);
	}

	private static void merge(int st, int en) {
		int mid = (st + en) / 2;

		int[] tmp = new int[en - st];
		int idx1 = st;
		int idx2 = mid;

		for (int i = 0; i < en - st; ++i) {
			if (idx1 < mid && idx2 < en) {
				tmp[i] = arr[idx1] <= arr[idx2] ? arr[idx1++] : arr[idx2++];
			} else if (idx1 == mid) {
				tmp[i] = arr[idx2++];
			} else if (idx2 == en) {
				tmp[i] = arr[idx1++];
			}
		}

		System.arraycopy(tmp, 0, arr, st, en - st);
	}

}
