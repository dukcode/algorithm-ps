package before.barkingdog.chap15_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1932 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] arr;
    private static int[][] sumArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        sumArr = new int[n][n];

        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x <= y; ++x) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        sumArr[0][0] = arr[0][0];
        for (int y = 1; y < n; ++y) {
            for (int x = 0; x <= y; ++x) {
                if (x == 0) {
                    sumArr[y][x] = sumArr[y - 1][x] + arr[y][x];
                    continue;
                }

                if (x == y) {
                    sumArr[y][x] = sumArr[y - 1][x - 1] + arr[y][x];
                    continue;
                }

                sumArr[y][x] = arr[y][x] + Math.max(sumArr[y - 1][x - 1], sumArr[y - 1][x]);
            }
        }

        bw.write(String.valueOf(Arrays.stream(sumArr[n - 1]).max().getAsInt()));

        bw.flush();
        bw.close();
        br.close();
    }

}
