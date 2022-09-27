package barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10816 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] cards;
    private static int m;
    private static int[] targets;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        cards = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        targets = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            targets[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            bw.write(String.valueOf(upperBound(target) - lowerBound(target)));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int lowerBound(int target) {
        int st = 0;
        int en = n;

        while (st < en) {
            int mid = (st + en) / 2;

            if (target <= cards[mid]) {
                en = mid;
            } else if (cards[mid] < target) {
                st = mid + 1;
            }
        }

        return st;
    }

    private static int upperBound(int target) {
        int st = 0;
        int en = n;

        while (st < en) {
            int mid = (st + en) / 2;

            if (target < cards[mid]) {
                en = mid;
            } else if (cards[mid] <= target) {
                st = mid + 1;
            }
        }

        return st;
    }

}
