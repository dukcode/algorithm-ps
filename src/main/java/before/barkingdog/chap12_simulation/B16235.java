package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class B16235 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static int k;

    private static int[][] ground;
    private static int[][] age5s;
    private static int[][] a;
    private static List<List<LinkedList<Integer>>> trees = new ArrayList<>();

    private static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ground = new int[n][n];
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                ground[y][x] = 5;
            }
        }

        a = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                a[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < n; ++y) {
            trees.add(new ArrayList<>());
            for (int x = 0; x < n; ++x) {
                trees.get(y).add(new LinkedList<>());
            }
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            trees.get(y).get(x).add(age);
        }

        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                Collections.sort(trees.get(y).get(x));
            }
        }

        while (k-- > 0) {
            age5s = new int[n][n];
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    LinkedList<Integer> live = trees.get(y).get(x);
                    LinkedList<Integer> dead = new LinkedList<>();

                    if (live.isEmpty()) {
                        continue;
                    }

                    // 봄
                    int size = live.size();
                    for (int i = 0; i < size; ++i) {
                        if (ground[y][x] >= live.peek()) {
                            if ((live.peek() + 1) % 5 == 0) {
                                age5s[y][x]++;
                            }

                            ground[y][x] -= live.peek();
                            live.offer(live.poll() + 1);
                        } else {
                            dead.offer(live.poll());
                        }

                    }

                    //여름
                    for (Integer age : dead) {
                        ground[y][x] += age / 2;
                    }
                }
            }

            // 가을
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    int num = age5s[y][x];
                    // 겨울
                    ground[y][x] += a[y][x];
                    for (int i = 0; i < 8; ++i) {
                        int ny = y + dy[i];
                        int nx = x + dx[i];

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        LinkedList<Integer> t = trees.get(ny).get(nx);
                        for (int j = 0; j < num; ++j) {
                            t.addFirst(1);
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                ans += trees.get(y).get(x).size();
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
