package before.barkingdog.chap16_greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B2457 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static Flower[] flowers;

    private static class Flower {

        Date start;
        Date end;

        public Flower(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public boolean bloomOn(Date date) {
            return start.compareTo(date) <= 0 && end.compareTo(date) > 0;
        }
    }

    private static int ans;

    private static class Date implements Comparable<Date> {

        int month;
        int day;

        public Date(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override public int compareTo(Date o) {
            if (this == o) {
                return 0;
            }

            if (this.month == o.month) {
                return this.day - o.day;
            }

            return this.month - o.month;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        flowers = new Flower[n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(new Date(startMonth, startDay), new Date(endMonth, endDay));
        }

        Date startDate = new Date(3, 1);
        Date endDate = new Date(11, 30);

        while (startDate.compareTo(endDate) <= 0) {
            List<Flower> bloomingFlowers = new ArrayList<>();
            for (Flower flower : flowers) {
                if (flower.bloomOn(startDate)) {
                    bloomingFlowers.add(flower);
                }
            }

            if (bloomingFlowers.size() == 0) {
                ans = 0;
                break;
            }

            Flower lastFallingFlower =
                    Collections.max(bloomingFlowers, (f1, f2) -> f1.end.compareTo(f2.end));

            startDate = lastFallingFlower.end;
            ans++;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
