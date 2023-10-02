package before.barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1920 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] arr;
    private static int m;
    private static int[] candidates;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        candidates = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            candidates[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < m; ++i) {
            bw.write(String.valueOf(binarySearch(candidates[i])));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int binarySearch(int target) {
        int st = 0;
        int en = n - 1;

        while (st <= en) {
            int mid = (st + en) / 2;

            if (arr[mid] < target) {
                st = mid + 1;
            } else if (target < arr[mid]) {
                en = mid - 1;
            } else {
                return 1;
            }
        }

        return 0;
    }

}
