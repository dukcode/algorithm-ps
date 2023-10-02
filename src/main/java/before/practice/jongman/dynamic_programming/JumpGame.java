package before.practice.jongman.dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpGame {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[][] board;
  private static int[][] cache;

  private static final int TRUE = 1;
  private static final int FALSE = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      board = new int[n][n];
      cache = new int[n][n];
      for (int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x < n; x++) {
          board[y][x] = Integer.parseInt(st.nextToken());
          Arrays.fill(cache[y], -1);
        }
      }

      bw.write(canWin(0, 0) == TRUE ? "YES" : "NO");
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int canWin(int y, int x) {
    if (y < 0 || y >= n || x < 0 || x >= n) {
      return FALSE;
    }

    if (y == n - 1 && x == n - 1) {
      return TRUE;
    }

    if (cache[y][x] != -1) {
      return cache[y][x];
    }

    int dist = board[y][x];

    return cache[y][x] = (canWin(y + dist, x) | canWin(y, x + dist));
  }


}
