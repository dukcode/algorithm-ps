package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B15686 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int m;

	private static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static List<Point> houses = new ArrayList<>();
	private static List<Point> candidates = new ArrayList<>();

	private static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				int block = Integer.parseInt(st.nextToken());
				if (block == 1) {
					houses.add(new Point(y, x));
				} else if (block == 2) {
					candidates.add(new Point(y, x));
				}
			}
		}

		int[] permutation = new int[candidates.size()];
		Arrays.fill(permutation, permutation.length - m, permutation.length, 1);
		do {
			int minDist = 0;
			for (Point house : houses) {
				int chickenDist = Integer.MAX_VALUE;
				for (int i = 0; i < candidates.size(); ++i) {
					if (permutation[i] == 1) {
						Point chicken = candidates.get(i);
						chickenDist = Math.min(chickenDist,
							Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x));
					}
				}
				minDist += chickenDist;
			}
			ans = Math.min(ans, minDist);
		}
		while (nextPermutation(permutation));

		bw.write(String.valueOf(ans));

		br.close();
		bw.close();
	}

	private static boolean nextPermutation(int[] arr) {
		int size = arr.length;
		int leftIdx = -1;
		for (int i = size - 2; i >= 0; --i) {
			if (arr[i] < arr[i + 1]) {
				leftIdx = i;
				break;
			}
		}

		if (leftIdx == -1) {
			return false;
		}

		int rightIdx = size - 1;
		while (arr[leftIdx] >= arr[rightIdx]) {
			rightIdx--;
		}

		swap(arr, leftIdx, rightIdx);

		int st = leftIdx + 1;
		int en = size - 1;

		while (st < en) {
			swap(arr, st, en);
			st++;
			en--;
		}

		return true;
	}

	private static void swap(int[] arr, int st, int en) {
		int tmp = arr[st];
		arr[st] = arr[en];
		arr[en] = tmp;
	}

}
