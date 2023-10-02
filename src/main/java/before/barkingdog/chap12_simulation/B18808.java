package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B18808 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int h;
    private static int w;
    private static int[][] paper;
    private static int numSticker;
    private static int r;
    private static int c;
    private static int size;
    private static int[][] sticker;

    private static int ans = 0;

    private static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        paper = new int[h][w];
        numSticker = Integer.parseInt(st.nextToken());

        Loop:
        for (int i = 0; i < numSticker; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            size = Math.max(r, c);
            sticker = new int[size][size];
            for (int y = 0; y < r; ++y) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < c; ++x) {
                    sticker[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for (int dir = 0; dir < 4; ++dir) {
                if (dir != 0) {
                    rotate();
                }
                Point firstPoint = getFirstPoint();
                for (int y = 0; y < h; ++y) {
                    for (int x = 0; x < w; ++x) {
                        if (paper[y][x] == 0 && isPossible(y, x, firstPoint)) {
                            stick(y, x, firstPoint);
                            continue Loop;
                        }
                    }
                }
            }
        }

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (paper[y][x] == 1) {
                    ans++;
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.close();
        br.close();
    }

    private static Point getFirstPoint() {
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (sticker[y][x] == 1) {
                    return new Point(y, x);
                }
            }
        }
        return null;
    }

    private static boolean isPossible(int y, int x, Point firstPoint) {

        for (int sy = 0; sy < size; ++sy) {
            for (int sx = 0; sx < size; ++sx) {
                if (sticker[sy][sx] == 0) {
                    continue;
                }

                int paperY = sy - firstPoint.y + y;
                int paperX = sx - firstPoint.x + x;

                if (paperY < 0 || paperY >= h || paperX < 0 || paperX >= w) {
                    return false;
                }

                if (paper[paperY][paperX] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void rotate() {
        for (int y = 0; y < size; ++y) {
            for (int x = y; x < size; ++x) {
                int temp = sticker[y][x];
                sticker[y][x] = sticker[x][y];
                sticker[x][y] = temp;
            }
        }

        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size / 2; ++x) {
                int temp = sticker[y][size - 1 - x];
                sticker[y][size - 1 - x] = sticker[y][x];
                sticker[y][x] = temp;
            }
        }

    }

    private static void stick(int y, int x, Point firstPoint) {

        for (int sy = 0; sy < size; ++sy) {
            for (int sx = 0; sx < size; ++sx) {
                if (sticker[sy][sx] == 0) {
                    continue;
                }
                int paperY = sy - firstPoint.y + y;
                int paperX = sx - firstPoint.x + x;
                paper[paperY][paperX] = sticker[sy][sx];
            }
        }
    }

}
