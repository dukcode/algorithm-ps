package barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20166 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;
    private static int k;
    private static char[][] board;
    private static String[] words;

    private static class Point {

        int y;
        int x;
        String str;

        public Point(int y, int x, String str) {
            this.y = y;
            this.x = x;
            this.str = str;
        }
    }

    private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static Map<String, Integer> map = new HashMap<>();
    private static int maxLength;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[h][w];
        for (int y = 0; y < h; ++y) {
            board[y] = br.readLine().toCharArray();
        }

        words = new String[k];
        for (int i = 0; i < k; i++) {
            String word = br.readLine();
            words[i] = word;
            map.put(word, 0);
            maxLength = Math.max(maxLength, word.length());
        }

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                Queue<Point> q = new LinkedList<>();
                q.add(new Point(y, x, "" + board[y][x]));

                while (!q.isEmpty()) {
                    Point cur = q.poll();

                    if (map.containsKey(cur.str)) {
                        map.put(cur.str, map.get(cur.str) + 1);
                    }

                    if (cur.str.length() >= maxLength) {
                        continue;
                    }

                    for (int i = 0; i < 8; ++i) {
                        int ny = (cur.y + dy[i] + h) % h;
                        int nx = (cur.x + dx[i] + w) % w;
                        q.offer(new Point(ny, nx, cur.str + board[ny][nx]));
                    }
                }
            }

        }

        for (String word : words) {
            bw.write(String.valueOf(map.get(word)));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
