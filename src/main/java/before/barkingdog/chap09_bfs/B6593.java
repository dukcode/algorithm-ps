package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B6593 {

    static int[][][] board;
    static int[][][] dist;

    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};

    static class Point {

        int z;
        int y;
        int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) {
                break;
            }

            board = new int[l][r][c];
            dist = new int[l][r][c];

            Queue<Point> q = new LinkedList<>();

            Point end = new Point(0, 0, 0);
            for (int z = 0; z < l; ++z) {
                for (int y = 0; y < r; ++y) {
                    String line = br.readLine();
                    for (int x = 0; x < c; ++x) {
                        char block = line.charAt(x);
                        switch (block) {
                            case '#':
                                board[z][y][x] = -1;
                                break;
                            case '.':
                                board[z][y][x] = 0;
                                break;
                            case 'S':
                                board[z][y][x] = 0;
                                dist[z][y][x] = 1;
                                q.offer(new Point(z, y, x));
                                break;
                            case 'E':
                                end = new Point(z, y, x);
                                break;
                            default:
                                break;
                        }
                    }
                }
                br.readLine();
            }

            int ans = -1;
            while (!q.isEmpty()) {
                Point cur = q.poll();

                if (cur.z == end.z && cur.y == end.y && cur.x == end.x) {
                    ans = dist[cur.z][cur.y][cur.x] - 1;
                }

                for (int i = 0; i < 6; ++i) {
                    int nz = cur.z + dz[i];
                    int ny = cur.y + dy[i];
                    int nx = cur.x + dx[i];

                    if (nz < 0 || nz >= l || ny < 0 || ny >= r || nx < 0 || nx >= c) {
                        continue;
                    }

                    if (board[nz][ny][nx] != 0 || dist[nz][ny][nx] != 0) {
                        continue;
                    }

                    dist[nz][ny][nx] = dist[cur.z][cur.y][cur.x] + 1;
                    q.offer(new Point(nz, ny, nx));

                }
            }

            if (ans == -1) {
                bw.write("Trapped!");
                bw.newLine();
            } else {
                bw.write("Escaped in " + String.valueOf(ans) + " minute(s).");
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
