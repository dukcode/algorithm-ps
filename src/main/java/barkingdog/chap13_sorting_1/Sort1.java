package barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

// O(n^2) Sort
public class Sort1 {

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

        // bubbleSort();
        // selectionSort();
        // insertionSort();

        printArr();

        bw.flush();
        bw.close();
        br.close();
    }

    public static void bubbleSort() {
        for (int i = n - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public static void selectionSort() {
        for (int i = 0; i < n - 1; ++i) {
            int minIdx = i;
            for (int j = i + 1; j < n; ++j) {
                minIdx = arr[minIdx] < arr[j] ? minIdx : j;
            }
            swap(i, minIdx);
        }
    }

    public static void insertionSort() {
        for (int i = 1; i < n; ++i) {
            int target = arr[i];
            int j = i - 1;

            while (j >= 0 && target < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = target;
        }
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
