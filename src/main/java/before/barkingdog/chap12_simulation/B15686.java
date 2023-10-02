package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15686 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static final List<Point> chickens = new ArrayList<>();
    private static final List<Point> houses = new ArrayList<>();

    private static int numChickens;
    private static boolean[] permutation;

    private static int ans = Integer.MAX_VALUE;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                int house = Integer.parseInt(st.nextToken());
                if (house == 1) {
                    houses.add(new Point(y, x));
                } else if (house == 2) {
                    chickens.add(new Point(y, x));
                }
            }
        }
        numChickens = chickens.size();
        permutation = new boolean[numChickens];

        func(0, 0);

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    public static void func(int idx, int count) {

        if (count == m) {
            ans = Math.min(ans, countChickenDist());
            return;
        }

        for (int i = idx; i < numChickens; ++i) {
            permutation[i] = true;
            func(i + 1, count + 1);
            permutation[i] = false;
        }
    }

    private static int countChickenDist() {
        int ret = 0;
        for (int j = 0; j < houses.size(); ++j) {
            int dist = Integer.MAX_VALUE;
            for (int i = 0; i < numChickens; ++i) {
                if (permutation[i]) {
                    dist = Math.min(dist, Math.abs(chickens.get(i).y - houses.get(j).y)
                            + Math.abs(chickens.get(i).x - houses.get(j).x));
                }
            }
            ret += dist;
        }

        return ret;
    }

}
