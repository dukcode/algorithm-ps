package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class B14891 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int k;
    private static int gearNum;
    private static int dir;

    private static final int CW = 1;
    private static final int CCW = -1;

    private static final int RIGHT_IDX = 2;
    private static final int LEFT_IDX = 6;

    private static List<Deque<Integer>> gears = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            Deque<Integer> d = new LinkedList<>();
            for (int j = 0; j < 8; ++j) {
                int magnet = line.charAt(j) - '0';
                d.offerLast(magnet);
            }
            gears.add(d);
        }

        k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            gearNum = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());

            rotateGears(gearNum, dir);
        }

        bw.write(String.valueOf(calculatePoint()));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void rotateGears(int gearNum, int dir) {
        int[] dirs = new int[4];
        Arrays.fill(dirs, 0);
        dirs[gearNum] = dir;

        int rightDir = dir;
        for (int i = gearNum + 1; i < 4; ++i) {
            List<Integer> leftGear = new ArrayList<>(gears.get(i - 1));
            List<Integer> rightGear = new ArrayList<>(gears.get(i));

            if (leftGear.get(RIGHT_IDX).equals(rightGear.get(LEFT_IDX))) {
                break;
            } else {
                rightDir *= -1;
                dirs[i] = rightDir;
            }
        }

        int leftDir = dir;
        for (int i = gearNum - 1; i >= 0; --i) {
            List<Integer> rightGear = new ArrayList<>(gears.get(i + 1));
            List<Integer> leftGear = new ArrayList<>(gears.get(i));

            if (leftGear.get(RIGHT_IDX).equals(rightGear.get(LEFT_IDX))) {
                break;
            } else {
                leftDir *= -1;
                dirs[i] = leftDir;
            }
        }

        for (int i = 0; i < 4; ++i) {
            rotateGear(i, dirs[i]);
        }
    }

    private static void rotateGear(int gearNum, int dir) {
        Deque<Integer> gear = gears.get(gearNum);

        if (dir == CW) {
            gear.offerFirst(gear.pollLast());
        } else if (dir == CCW) {
            gear.offerLast(gear.pollFirst());
        }
    }

    private static int calculatePoint() {
        int point = 0;
        for (int i = 0; i < 4; ++i) {
            List<Integer> gear = new ArrayList<>(gears.get(i));

            point += gear.get(0) << i;
        }

        return point;
    }

}
