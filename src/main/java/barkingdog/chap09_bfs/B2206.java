package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206 {

    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, 1, -1, 0};


    static int[][] board;
    static int[][] distLeft;
    static int[][] distConsumed;

    static class Point {

        int y;
        int x;
        int cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        distLeft = new int[h][w];
        distConsumed = new int[h][w];

        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                board[y][x] = line.charAt(x) - '0';
            }
        }

        Queue<Point> q = new LinkedList<>();

        distLeft[0][0] = 1;
        distConsumed[0][0] = 1;
        q.offer(new Point(0, 0, 1));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    if (cur.cnt == 0) {
                        continue;
                    }

                    if (distConsumed[ny][nx] != 0
                            && distConsumed[ny][nx] <= distLeft[cur.y][cur.x] + 1) {
                        continue;
                    }

                    distConsumed[ny][nx] = distLeft[cur.y][cur.x] + 1;
                    q.offer(new Point(ny, nx, 0));

                } else {

                    if (cur.cnt == 1) {
                        if (distLeft[ny][nx] != 0
                                && distLeft[ny][nx] <= distLeft[cur.y][cur.x] + 1) {
                            continue;
                        }
                        distLeft[ny][nx] = distLeft[cur.y][cur.x] + 1;
                    } else {
                        if (distConsumed[ny][nx] != 0
                                && distConsumed[ny][nx] <= distConsumed[cur.y][cur.x] + 1) {
                            continue;
                        }
                        distConsumed[ny][nx] = distConsumed[cur.y][cur.x] + 1;
                    }

                    q.offer(new Point(ny, nx, cur.cnt));
                }
            }
        }

        // 0 0 -> -1
        // 0 0 이고 크기 1 -> 1
        // 하나만 0 일 때 -> 반대
        // 둘다 0 아닐 때 -> 작은거

        int ans = Math.min(distLeft[h - 1][w - 1], distConsumed[h - 1][w - 1]);

        if (distLeft[h - 1][w - 1] == 0 || distConsumed[h - 1][w - 1] == 0) {
            ans = distLeft[h - 1][w - 1] == 0 ? distConsumed[h - 1][w - 1] : distLeft[h - 1][w - 1];
        }

        if (distLeft[h - 1][w - 1] == 0 && distConsumed[h - 1][w - 1] == 0) {
            if (h == 1 && w == 1) {
                ans = 1;
            } else {
                ans = -1;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
