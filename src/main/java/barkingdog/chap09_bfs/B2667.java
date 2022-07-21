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

public class B2667 {

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] board;
    static boolean[][] visited;

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

        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        for (int y = 0; y < n; ++y) {
            String line = br.readLine();
            for (int x = 0; x < n; ++x) {
                board[y][x] = line.charAt(x) - '0';
            }
        }

        List<Integer> areas = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        int count = 0;
        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < n; ++x) {
                if (board[y][x] == 0 || visited[y][x]) {
                    continue;
                }

                visited[y][x] = true;
                q.offer(new Point(y, x));
                count++;

                int area = 0;
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    area++;

                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                            continue;
                        }

                        if (visited[ny][nx] || board[ny][nx] == 0) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
                areas.add(area);
            }
        }

        bw.write(String.valueOf(count));
        bw.newLine();

        Collections.sort(areas);
        for (Integer area : areas) {
            bw.write(String.valueOf(area));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
