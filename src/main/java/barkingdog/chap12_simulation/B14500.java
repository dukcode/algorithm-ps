package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14500 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int h;
    private static int w;

    private static int[][] board;
    private static List<int[][]> tetrominos = new ArrayList<>();

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        tetrominos.add(new int[][]{{1, 1, 1, 1}});
        tetrominos.add(new int[][]{{1},
                {1},
                {1},
                {1}});

        tetrominos.add(new int[][]{{1, 1},
                {1, 1}});

        tetrominos.add(new int[][]{{1, 0},
                {1, 0},
                {1, 1}});
        tetrominos.add(new int[][]{{1, 1, 1},
                {1, 0, 0}});
        tetrominos.add(new int[][]{{1, 1},
                {0, 1},
                {0, 1}});
        tetrominos.add(new int[][]{{0, 0, 1},
                {1, 1, 1}});

        tetrominos.add(new int[][]{{0, 1},
                {0, 1},
                {1, 1}});
        tetrominos.add(new int[][]{{1, 0, 0},
                {1, 1, 1}});
        tetrominos.add(new int[][]{{1, 1},
                {1, 0},
                {1, 0}});
        tetrominos.add(new int[][]{{1, 1, 1},
                {0, 0, 1}});

        tetrominos.add(new int[][]{{1, 0},
                {1, 1},
                {0, 1}});
        tetrominos.add(new int[][]{{0, 1, 1},
                {1, 1, 0}});

        tetrominos.add(new int[][]{{0, 1},
                {1, 1},
                {1, 0}});
        tetrominos.add(new int[][]{{1, 1, 0},
                {0, 1, 1}});

        tetrominos.add(new int[][]{{1, 1, 1},
                {0, 1, 0}});
        tetrominos.add(new int[][]{{1, 0},
                {1, 1},
                {1, 0}});
        tetrominos.add(new int[][]{{0, 1, 0},
                {1, 1, 1}});
        tetrominos.add(new int[][]{{0, 1},
                {1, 1},
                {0, 1}});

        board = new int[h][w];
        for (int y = 0; y < h; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; ++x) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                for (int i = 0; i < tetrominos.size(); ++i) {
                    int[][] tetromino = tetrominos.get(i);

                    if (y + tetromino.length - 1 >= h || x + tetromino[0].length - 1 >= w) {
                        continue;
                    }

                    int sum = 0;
                    for (int ty = 0; ty < tetromino.length; ++ty) {
                        for (int tx = 0; tx < tetromino[0].length; ++tx) {
                            sum += board[y + ty][x + tx] * tetromino[ty][tx];
                        }
                    }

                    ans = Math.max(ans, sum);
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

}
