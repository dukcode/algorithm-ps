package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1926 {

    public static int[][] board;
    public static boolean[][] visited;

    public static int[] dy = {1, 0, -1, 0};
    public static int[] dx = {0, 1, 0, -1};

    public static class Axis {

        int y;
        int x;

        public Axis(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st1.nextToken());
        int w = Integer.parseInt(st1.nextToken());

        board = new int[h][w];
        visited = new boolean[h][w];
        for (int y = 0; y < h; ++y) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st2.nextToken());
            }
        }

        int maxArea = 0;
        int cnt = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (visited[y][x] || board[y][x] == 0) {
                    continue;
                }

                cnt++;
                Queue<Axis> q = new LinkedList<>();
                visited[y][x] = true;
                q.offer(new Axis(y, x));
                int area = 0;

                while (!q.isEmpty()) {
                    area++;
                    Axis cur = q.poll();
                    for (int i = 0; i < 4; ++i) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                            continue;
                        }

                        if (visited[ny][nx] || board[ny][nx] == 0) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        q.offer(new Axis(ny, nx));
                    }
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        bw.write(String.valueOf(cnt));
        bw.newLine();
        bw.write(String.valueOf(maxArea));

        bw.flush();
        bw.close();
        br.close();
    }

}
