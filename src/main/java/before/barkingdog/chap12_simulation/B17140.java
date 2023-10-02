package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B17140 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int r;
    private static int c;
    private static int k;

    private static List<List<Integer>> arr = new ArrayList<>();

    private static int ans;

    private static class Num {

        int num;
        int appearance;

        public Num(int num, int appearance) {
            this.num = num;
            this.appearance = appearance;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int y = 0; y < 3; ++y) {
            st = new StringTokenizer(br.readLine());
            arr.add(new ArrayList<>());
            List<Integer> line = arr.get(y);
            for (int x = 0; x < 3; ++x) {
                line.add(Integer.parseInt(st.nextToken()));
            }
        }

        while ((arr.size() <= r || arr.get(0).size() <= c) || arr.get(r).get(c) != k) {
            ans++;
            if (ans > 100) {
                ans = -1;
                break;
            }

            if (arr.size() >= arr.get(0).size()) {
                calculateR();
            } else {
                calculateC();
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void calculateC() {
        rotateCCW();
        calculateR();
        rotateCW();
    }

    private static void rotateCW() {
        int rowSize = arr.size();
        int colSize = arr.get(0).size();

        List<List<Integer>> newArr = new ArrayList<>();
        for (int i = 0; i < colSize; ++i) {
            newArr.add(new ArrayList<>(Collections.nCopies(rowSize, 0)));
        }

        for (int y = 0; y < rowSize; ++y) {
            for (int x = 0; x < colSize; ++x) {
                newArr.get(x).set(rowSize - 1 - y, arr.get(y).get(x));
            }
        }

        arr = newArr;
    }

    private static void rotateCCW() {
        int rowSize = arr.size();
        int colSize = arr.get(0).size();

        List<List<Integer>> newArr = new ArrayList<>();
        for (int i = 0; i < colSize; ++i) {
            newArr.add(new ArrayList<>(Collections.nCopies(rowSize, 0)));
        }

        for (int y = 0; y < rowSize; ++y) {
            for (int x = 0; x < colSize; ++x) {
                newArr.get(colSize - 1 - x).set(y, arr.get(y).get(x));
            }
        }

        arr = newArr;
    }

    private static void calculateR() {
        int maxSize = 0;
        for (int i = 0; i < arr.size(); ++i) {
            List<Integer> row = arr.get(i);
            List<Integer> newRow = getNewLine(row);

            arr.set(i, newRow);

            maxSize = Math.max(maxSize, newRow.size());
        }

        for (int i = 0; i < arr.size(); ++i) {
            while (arr.get(i).size() != maxSize) {
                arr.get(i).add(0);
            }
        }

    }

    private static List<Integer> getNewLine(List<Integer> line) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : line) {
            if (num == 0) {
                continue;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Num> pq = new PriorityQueue<>((n1, n2) -> {
            if (n1.appearance == n2.appearance) {
                return n1.num - n2.num;
            }

            return n1.appearance - n2.appearance;
        });
        for (Integer n : map.keySet()) {
            pq.add(new Num(n, map.get(n)));
        }

        ArrayList<Integer> ret = new ArrayList<>();
        while (!pq.isEmpty()) {
            Num n = pq.poll();
            ret.add(n.num);
            ret.add(n.appearance);
        }

        return ret;
    }
}
