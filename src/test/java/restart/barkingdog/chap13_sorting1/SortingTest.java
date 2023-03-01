package restart.barkingdog.chap13_sorting1;

import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class SortingTest {

	@Test
	public void test1() {
		Random random = new Random();
		int size = 10;
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = random.nextInt(10);
		}

		printArr(arr, "before");
		mergeSort(arr, 0, arr.length);
		printArr(arr, "after");
	}

	@Test
	public void test2() {
		Random random = new Random();
		int size = 10;
		int[] arr = new int[size];

		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < size; j++) {
				arr[j] = random.nextInt(10);
			}

			printArr(arr, "before");
			quickSort(arr, 0, size);
			printArr(arr, "after");
			for (int j = 1; j < size; j++) {
				assertThat(arr[j - 1]).isLessThanOrEqualTo(arr[j]);
			}
		}
	}

	void mergeSort(int[] arr, int st, int en) {
		if (st + 1 == en) {
			return;
		}

		int mid = (st + en) / 2;
		mergeSort(arr, st, mid);
		mergeSort(arr, mid, en);
		merge(arr, st, en);
	}

	void merge(int[] arr, int st, int en) {
		int mid = (st + en) / 2;
		int size = en - st;

		int idx1 = st;
		int idx2 = mid;

		int[] sortedArr = new int[size];
		for (int i = 0; i < en - st; ++i) {
			if (idx1 == mid) {
				sortedArr[i] = arr[idx2];
				idx2++;
				continue;
			}

			if (idx2 == en) {
				sortedArr[i] = arr[idx1];
				idx1++;
				continue;
			}

			sortedArr[i] = arr[idx1] <= arr[idx2] ? arr[idx1++] : arr[idx2++];
		}

		System.arraycopy(sortedArr, 0, arr, st, size);
	}

	void quickSort(int[] arr, int st, int en) {

		int size = en - st;
		if (size <= 1) {
			return;
		}

		int pivotIdx = st;
		int pivot = arr[st];

		int leftIdx = st + 1;
		int rightIdx = en - 1;
		while (true) {
			while (leftIdx != en && arr[leftIdx] <= pivot) {
				leftIdx++;
			}

			while (arr[rightIdx] > pivot) {
				rightIdx--;
			}

			if (leftIdx >= rightIdx) {
				break;
			}
			swap(arr, leftIdx, rightIdx);
		}
		swap(arr, pivotIdx, rightIdx);

		quickSort(arr, st, rightIdx);
		quickSort(arr, rightIdx + 1, en);
	}

	private static void swap(int[] arr, int st, int pivot) {
		int tmp = arr[pivot];
		arr[pivot] = arr[st];
		arr[st] = tmp;
	}

	void printArr(int[] arr, String name) {

		System.out.print(name + " : [");
		for (int num : arr) {
			System.out.printf("%2d ", num);
		}
		System.out.println("]");
	}

}