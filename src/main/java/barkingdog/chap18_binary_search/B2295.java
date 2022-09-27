package barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class B2295 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;
    private static int[] arr;
    private static List<Integer> twoSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; j++) {
                twoSum.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(twoSum);

        int ans = 0;
        Loop:
        for (int i = n - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (Collections.binarySearch(twoSum, arr[i] - arr[j]) >= 0) {
                    ans = arr[i];
                    break Loop;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
