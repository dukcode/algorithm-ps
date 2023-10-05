package jongman.chap23_priority_queue_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RunningMedian {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int a;
  private static int b;

  private static final int MOD = 2009_0711;

  private static class RNG {

    int seed;
    int a;
    int b;

    public RNG(int seed, int a, int b) {
      this.seed = seed;
      this.a = a;
      this.b = b;
    }

    public int next() {
      int ret = seed;
      seed = (int) (((long) seed * a + b) % MOD);
      return ret;
    }
  }

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());

      int ans = solve();
      bw.write(String.valueOf(ans));
      bw.newLine();
    }
    br.close();
    bw.close();
  }

  private static int solve() {
    PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> right = new PriorityQueue<>();

    RNG rng = new RNG(1983, a, b);
    int ret = 0;
    for (int i = 0; i < n; ++i) {
      int num = rng.next();
      if (left.size() == right.size()) {
        left.offer(num);
      } else {
        right.offer(num);
      }

      if (!right.isEmpty() && left.peek() > right.peek()) {
        int toLeft = right.poll();
        int toRight = left.poll();

        left.offer(toLeft);
        right.offer(toRight);
      }
      ret = (ret + left.peek()) % MOD;
    }
    return ret;
  }

}
