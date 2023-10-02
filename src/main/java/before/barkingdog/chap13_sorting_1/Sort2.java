package before.barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

// O(n log n) Sort
public class Sort2 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        // br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        printArr();
        // mergeSort(0, n);
        quickSort(0, n);
        printArr();

        bw.flush();
        bw.close();
        br.close();
    }

    public static void mergeSort(int start, int end) {
        if (start + 1 == end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(start, mid);
        mergeSort(mid, end);
        merge(start, end);
    }

    private static void merge(int start, int end) {
        int mid = (start + end) / 2;
        int idxA = start;
        int idxB = mid;

        int[] ans = new int[end - start];

        for (int i = start; i < end; ++i) {
            if (idxB == end) {
                ans[i - start] = arr[idxA++];
            } else if (idxA == mid) {
                ans[i - start] = arr[idxB++];
            } else if (arr[idxA] < arr[idxB]) { // stable sort
                ans[i - start] = arr[idxA++];
            } else {
                ans[i - start] = arr[idxB++];
            }
        }

        System.arraycopy(ans, 0, arr, start, end - start);
    }

    private static void heapSort() {

    }

    private static void quickSort(int start, int end) {
        if (start + 1 >= end) {
            return;
        }

        int pivot = start;

        int l = start + 1;
        int r = end - 1;
        while (true) {
            while (l <= r && arr[pivot] >= arr[l]) {
                l++;
            }

            while (l <= r && arr[r] > arr[pivot]) {
                r--;
            }

            if (r < l) {
                break;
            }

            swap(r, l);
        }
        swap(pivot, r);

        quickSort(start, r);
        quickSort(r + 1, end);
    }

    private static void swap(int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static void printArr() throws IOException {
        for (int i = 0; i < n; ++i) {
            bw.write(String.valueOf(arr[i]));
            bw.write(' ');
        }
        bw.newLine();
    }


}
