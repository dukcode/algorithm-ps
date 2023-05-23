package restart.barkingdog.chap11_backtracking;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class NextPermutationTest {

	@Test
	void test() {
		int[] arr = {0, 1, 2, 3, 4};

		int cnt = 1;
		do {
			System.out.println(Arrays.toString(arr));
		} while (nextPermutation(arr));

		System.out.println("cnt = " + cnt);
	}

	private static boolean nextPermutation(int[] arr) {
		int length = arr.length;

		int leftIdx = length - 2;
		while (leftIdx >= 0 && arr[leftIdx] >= arr[leftIdx + 1]) {
			leftIdx--;
		}

		if (leftIdx == -1) {
			return false;
		}

		int rightIdx = length - 1;
		while (arr[leftIdx] >= arr[rightIdx]) {
			rightIdx--;
		}

		swap(arr, leftIdx, rightIdx);

		int start = leftIdx + 1;
		int end = length - 1;
		while (start < end) {
			swap(arr, start, end);
			start++;
			end--;
		}

		return true;
	}

	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}