package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B17298_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] ans = new int[N];

        for (int i = N - 1; i >= 0; --i) {
            int idx = i + 1;

            while (idx < N && arr[i] >= arr[idx]) {
                idx = ans[idx];
            }
            ans[i] = idx;
        }

        for (int idx : ans) {
            bw.write(idx == N ? "-1" : String.valueOf(arr[idx]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }


}
