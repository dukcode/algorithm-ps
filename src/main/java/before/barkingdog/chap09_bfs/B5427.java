package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5427 {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] dist1;
    static int[][] dist2;

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            dist1 = new int[h][w];
            dist2 = new int[h][w];

            Queue<Point> q1 = new LinkedList<>();
            Queue<Point> q2 = new LinkedList<>();

            for (int y = 0; y < h; ++y) {
                String line = br.readLine();
                for (int x = 0; x < w; ++x) {
                    char block = line.charAt(x);
                    switch (block) {
                        case '#':
                            dist1[y][x] = -1;
                            dist2[y][x] = -1;
                            break;
                        case '@':
                            dist2[y][x] = 1;
                            q2.offer(new Point(y, x));
                            break;
                        case '*':
                            dist1[y][x] = 1;
                            q1.offer(new Point(y, x));
                            break;
                        default:
                            break;
                    }
                }
            }

            while (!q1.isEmpty()) {
                Point cur = q1.poll();

                for (int i = 0; i < 4; ++i) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                        continue;
                    }

                    if (dist1[ny][nx] != 0) {
                        continue;
                    }

                    dist1[ny][nx] = dist1[cur.y][cur.x] + 1;
                    q1.offer(new Point(ny, nx));
                }
            }

            int ans = -1;
            Loop:
            while (!q2.isEmpty()) {
                Point cur = q2.poll();

                for (int i = 0; i < 4; ++i) {
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                        ans = dist2[cur.y][cur.x];
                        break Loop;
                    }

                    if (dist2[ny][nx] != 0) {
                        continue;
                    }

                    if (dist1[ny][nx] != 0 && dist1[ny][nx] <= dist2[cur.y][cur.x] + 1) {
                        continue;
                    }

                    dist2[ny][nx] = dist2[cur.y][cur.x] + 1;
                    q2.offer(new Point(ny, nx));
                }
            }

            if (ans == -1) {
                bw.write("IMPOSSIBLE");
            } else {
                bw.write(String.valueOf(ans));
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
