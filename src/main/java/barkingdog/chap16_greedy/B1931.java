package barkingdog.chap16_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1931 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static Class[] classes;

    private static class Class {

        int start;
        int end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        classes = new Class[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            classes[i] = new Class(start, end);
        }

        Arrays.sort(classes, (c1, c2) -> {
            if (c1.end == c2.end) {
                return c1.start - c2.start;
            }
            return c1.end - c2.end;
        });

        int end = 0;
        for (int i = 0; i < n; ++i) {
            if (end > classes[i].start) {
                continue;
            }

            end = classes[i].end;
            ans++;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
