package before.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16920 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int p;
    private static int[] s;

    private static int[][] board;

    private static final int WALL = -1;

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {-1, 1, 0, 0};

    private static class Point {

        int y;
        int x;
        int s;

        public Point(int y, int x, int s) {
            this.y = y;
            this.x = x;
            this.s = s;
        }
    }

    private static int[] ansArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = new int[p + 1];
        ansArr = new int[p + 1];
        for (int i = 1; i <= p; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        board = new int[h][w];
        List<Queue<Point>> qs = new ArrayList<>();
        for (int i = 0; i <= p; ++i) {
            qs.add(new LinkedList<>());
        }

        for (int y = 0; y < h; ++y) {
            String line = br.readLine();
            for (int x = 0; x < w; ++x) {
                char c = line.charAt(x);
                if (Character.isDigit(c)) {
                    int player = c - '0';
                    board[y][x] = player;
                    ansArr[player]++;
                    qs.get(player).offer(new Point(y, x, 0));
                } else if (c == '#') {
                    board[y][x] = -1;
                }
            }
        }

        while (true) {
            boolean isExpanded = false;
            for (int player = 1; player <= p; ++player) {
                Queue<Point> q = qs.get(player);
                Queue<Point> nq = new LinkedList<>();
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    if (cur.s == s[player]) {
                        nq.offer(new Point(cur.y, cur.x, 0));
                        continue;
                    }

                    for (int i = 0; i < 4; i++) {
                        int ny = cur.y + dy[i];
                        int nx = cur.x + dx[i];

                        if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
                            continue;
                        }

                        if (board[ny][nx] != 0) {
                            continue;
                        }

                        board[ny][nx] = board[cur.y][cur.x];
                        isExpanded = true;
                        ansArr[player]++;
                        q.offer(new Point(ny, nx, cur.s + 1));
                    }
                }

                qs.set(player, nq);
            }

            if (!isExpanded) {
                break;
            }
        }

        for (int i = 1; i <= p; i++) {
            bw.write(String.valueOf(ansArr[i]));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
