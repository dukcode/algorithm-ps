package barkingdog.chap13_sorting_1;

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
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(0, n);

        for (int i = 0; i < n; ++i) {
            bw.write(String.valueOf(arr[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void mergeSort(int start, int end) {
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

        int[] tmp = new int[end - start];
        int idxA = start;
        int idxB = mid;

        for (int i = start; i < end; ++i) {
            if (idxA == mid) {
                tmp[i - start] = arr[idxB++];
            } else if (idxB == end) {
                tmp[i - start] = arr[idxA++];
            } else if (arr[idxA] <= arr[idxB]) {
                tmp[i - start] = arr[idxA++];
            } else {
                tmp[i - start] = arr[idxB++];
            }
        }

        System.arraycopy(tmp, 0, arr, start, end - start);
    }

}
