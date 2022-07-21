package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2583 {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] board;

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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        board = new int[h][w];

        while (k-- > 0) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            for (int y = y1; y < y2; ++y) {
                for (int x = x1; x < x2; ++x) {
                    board[y][x] = -1;
                }
            }
        }

        int count = 0;
        List<Integer> areas = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (board[y][x] != 0) {
                    continue;
                }

                board[y][x] = 1;
                count++;
                q.offer(new Point(y, x));

                int area = 0;
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    area++;

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                            continue;
                        }

                        if (board[ny][nx] != 0) {
                            continue;
                        }

                        board[ny][nx] = 1;
                        q.offer(new Point(ny, nx));
                    }
                }
                areas.add(area);
            }
        }
        Collections.sort(areas);

        bw.write(String.valueOf(count));
        bw.newLine();
        for (Integer area : areas) {
            bw.write(String.valueOf(area));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
