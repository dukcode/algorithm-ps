package before.practice.jongman.divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Fence {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[] heights;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      st = new StringTokenizer(br.readLine());
      heights = new int[n];
      for (int i = 0; i < n; ++i) {
        heights[i] = Integer.parseInt(st.nextToken());
      }

      bw.write(String.valueOf(calculateMaxArea(0, n)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int calculateMaxArea(int from, int to) {
    if (from + 1 == to) {
      return heights[from];
    }

    int mid = (from + to) / 2;
    int ret = Math.max(calculateMaxArea(from, mid), calculateMaxArea(mid, to));

    int l = mid - 1;
    int r = mid;
    int h = Math.min(heights[l], heights[r]);
    ret = Math.max(ret, h * 2);

    while (from < l || r < to - 1) {
      if (r < to - 1 && (l == from || heights[l - 1] <= heights[r + 1])) {
        h = Math.max(h, ++r);
      } else {
        h = Math.max(h, --l);
      }

      ret = Math.max(ret, h * (r - l + 1));
    }

    return ret;
  }


}
