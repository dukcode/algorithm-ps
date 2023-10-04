package jongman.chap20_string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JaehaSafe {

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int c;
  private static int n;
  private static String[] targets;

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      input();
      int ans = solve();
      bw.write(String.valueOf(ans));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve() {
    int ret = 0;
    for (int i = 1; i < n + 1; ++i) {
      String original = targets[i - 1];
      String target = targets[i];
      if (i % 2 == 1) {
        ret += countClockwiseShifts(original, target);
      } else {
        ret += countCounterClockwiseShifts(original, target);
      }
    }

    return ret;
  }

  private static int countCounterClockwiseShifts(String original, String target) {
    return kmp(original + original, target);
  }

  private static int kmp(String str, String target) {
    int m = str.length();
    int n = target.length();

    int[] pm = calPartialMatches(target);

    int matched = 0;
    for (int i = 0; i < m; ++i) {
      while (matched > 0 && target.charAt(matched) != str.charAt(i)) {
        matched = pm[matched - 1];
      }

      if (target.charAt(matched) == str.charAt(i)) {
        matched++;
        if (matched == n) {
          return i - n + 1;
        }
      }
    }
    return Integer.MIN_VALUE;
  }

  private static int[] calPartialMatches(String str) {
    int n = str.length();

    int[] ret = new int[n];

    int matched = 0;
    for (int i = 1; i < n; ++i) {
      while (matched > 0 && str.charAt(matched) != str.charAt(i)) {
        matched = ret[matched - 1];
      }

      if (str.charAt(matched) == str.charAt(i)) {
        matched++;
        ret[i] = matched;
      }
    }

    return ret;
  }

  private static int countClockwiseShifts(String original, String target) {
    return countCounterClockwiseShifts(target, original);
  }

  private static void input() throws IOException {
    n = Integer.parseInt(br.readLine());

    targets = new String[n + 1];
    for (int i = 0; i < n + 1; ++i) {
      targets[i] = br.readLine();
    }
  }
}
