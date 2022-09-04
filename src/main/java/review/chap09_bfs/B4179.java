package review.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {-1, 1, 0, 0};

    private static int h;
    private static int w;

    private static int[][] board;
    private static int[][] distFire;
    private static int[][] distPerson;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int ans;

    private static Queue<Point> fireQ = new LinkedList<>();
    private static Queue<Point> peopleQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        distFire = new int[h][w];
        distPerson = new int[h][w];

        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                char block = line.charAt(x);

                switch (block) {
                    case '#':
                        board[y][x] = -1;
                        break;
                    case 'J':
                        distPerson[y][x] = 1;
                        peopleQ.offer(new Point(y, x));
                        break;
                    case 'F':
                        distFire[y][x] = 1;
                        fireQ.offer(new Point(y, x));
                        break;
                    default:
                        break;
                }
            }
        }

        while (!fireQ.isEmpty()) {
            Point cur = fireQ.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    continue;
                }

                if (board[ny][nx] != 0 || distFire[ny][nx] != 0) {
                    continue;
                }

                distFire[ny][nx] = distFire[cur.y][cur.x] + 1;
                fireQ.offer(new Point(ny, nx));
            }
        }

        Loop:
        while (!peopleQ.isEmpty()) {
            Point cur = peopleQ.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                    ans = distPerson[cur.y][cur.x];
                    break Loop;
                }

                if (board[ny][nx] != 0
                        || (distFire[ny][nx] != 0
                        && distFire[ny][nx] <= distPerson[cur.y][cur.x] + 1)
                        || distPerson[ny][nx] != 0) {
                    continue;
                }

                distPerson[ny][nx] = distPerson[cur.y][cur.x] + 1;
                peopleQ.offer(new Point(ny, nx));
            }
        }

        if (ans == 0) {
            bw.write("IMPOSSIBLE");
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
