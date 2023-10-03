package jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * <a href=https://www.algospot.com/judge/problem/read/FENCE>URL</a>
 */
public class Fence {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[] arr;
  private static Deque<Integer> stk;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      input();
      stk = new ArrayDeque<>(n);
      stk.offerLast(0);

      int ret = 0;
      for (int idx = 0; idx <= n; ++idx) {
        while (!stk.isEmpty() && arr[stk.peekLast()] > arr[idx]) {
          int height = arr[stk.pollLast()];
          int left = stk.isEmpty() ? -1 : stk.peekLast();
          int width = idx - left - 1;
          ret = Math.max(ret, height * width);
        }
        stk.offerLast(idx);
      }

      bw.write(String.valueOf(ret));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static void input() throws IOException {
    n = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    arr = new int[n + 1];
    for (int i = 0; i < n; ++i) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }


}
