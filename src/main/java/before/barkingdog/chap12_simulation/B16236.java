package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[][] fishbowl;
    private static int[][] dist;

    private static Point shark;
    private static int sharkSize;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[] dy = {-1, 0, 0, 1};
    private static final int[] dx = {0, -1, 1, 0};

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        fishbowl = new int[n][n];
        for (int y = 0; y < n; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) {
                int size = Integer.parseInt(st.nextToken());

                if (size == 9) {
                    shark = new Point(y, x);
                    sharkSize = 2;
                    continue;
                }

                fishbowl[y][x] = size;
            }
        }

        int numAte = 0;
        boolean eaten = true;
        while (eaten) {
            eaten = false;
            dist = new int[n][n];
            Queue<Point> q = new LinkedList<>();

            q.offer(shark);
            dist[shark.y][shark.x] = 1;

            int minDist = Integer.MAX_VALUE;
            Point minPos = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

            Loop:
            while (!q.isEmpty()) {
                Point cur = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                        continue;
                    }

                    if (dist[ny][nx] != 0) {
                        continue;
                    }

                    if (fishbowl[ny][nx] > sharkSize) {
                        continue;
                    }

                    if (dist[cur.y][cur.x] > minDist) {
                        break Loop;
                    }

                    if (fishbowl[ny][nx] != 0 && fishbowl[ny][nx] < sharkSize) {

                        eaten = true;
                        minDist = Math.min(minDist, dist[cur.y][cur.x]);

                        if (minPos.y == ny) {
                            minPos.x = Math.min(minPos.x, nx);
                        } else if (ny < minPos.y) {
                            minPos.y = ny;
                            minPos.x = nx;
                        }
                    }

                    dist[ny][nx] = dist[cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx));
                }
            }

            if (eaten) {
                ans += minDist;
                fishbowl[minPos.y][minPos.x] = 0;
                shark = minPos;
                numAte++;
                if (numAte == sharkSize) {
                    numAte = 0;
                    sharkSize++;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
