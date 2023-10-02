package jongman.chap17_partial_sum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href=https://www.algospot.com/judge/problem/read/CHRISTMAS>URL</a>
 */
public class Christmas {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static final int MOD = 20_091_101;

  private static int t;
  private static int n;
  private static int k;
  private static int[] d;

  private static int[] psum;

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      input();

      int ans1 = countOrderingOnceMethod();

      int ans2 = countMaxOrdering();

      bw.write(String.valueOf(ans1));
      bw.write(' ');
      bw.write(String.valueOf(ans2));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  /**
   * 0번 부터 idx번째 까지의 선물을 겹치지 않고 주문할 수 있는 최대 주분 횟수를 구한다.
   */
  private static int countMaxOrdering() {
    int[] ret = new int[n];

    // cache[s] = psum[]이 s였던 마지막 위치
    int[] cache = new int[k];
    Arrays.fill(cache, -1);

    for (int i = 0; i < n; ++i) {
      // i번째 상자를 고려하지 않은 경우
      if (i > 0) {
        ret[i] = ret[i - 1];
      }

      int idx = cache[psum[i]]; // psum[i]를 본 마지막 위치
      if (idx != -1) {
        ret[i] = Math.max(ret[i], ret[idx] + 1);
      } else {
        if (psum[i] == 0) {
          ret[i] = Math.max(ret[i], 1);
        }
      }

      cache[psum[i]] = i;
    }

    return ret[n - 1];
  }

  private static int countOrderingOnceMethod() {
    long[] count = new long[k];
    for (int i = 0; i < n; ++i) {
      count[psum[i]]++;
    }

    int ret = (int) (count[0] % MOD);
    for (int i = 0; i < k; ++i) {
      ret = (int) ((ret + count[i] * (count[i] - 1) / 2) % MOD);
    }
    return ret;
  }

  private static void input() throws IOException {
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    d = new int[n];
    for (int i = 0; i < n; ++i) {
      d[i] = Integer.parseInt(st.nextToken());
    }

    psum = new int[n];
    psum[0] = d[0] % k;
    for (int i = 1; i < n; ++i) {
      psum[i] = (psum[i - 1] + d[i]) % k;
    }
  }

}
