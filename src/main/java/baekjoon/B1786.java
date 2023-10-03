package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href=https://www.acmicpc.net/problem/1786>URL</a>
 */
public class B1786 {

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static String t;
  private static String p;

  private static int count;


  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    input();
    List<Integer> ans = solve();
    output(ans);

    br.close();
    bw.close();
  }

  private static int[] calculatePartialMatches() {
    int n = p.length();
    int[] partialMatches = new int[n];

    int begin = 1;
    int matched = 0;
    while (begin + matched < n) {
      if (p.charAt(begin + matched) == p.charAt(matched)) {
        matched++;
        partialMatches[begin + matched - 1] = matched;
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - partialMatches[matched - 1];
          matched = partialMatches[matched - 1];
        }
      }
    }

    return partialMatches;
  }

  private static List<Integer> solve() {
    List<Integer> ret = new ArrayList<>();

    int[] partialMatches = calculatePartialMatches();

    int m = t.length();
    int n = p.length();

    int begin = 0;
    int matched = 0;
    while (begin <= m - n) {

      if (matched < n && t.charAt(begin + matched) == p.charAt(matched)) {
        matched++;

        if (matched == n) {
          ret.add(begin);
          count++;
        }
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - partialMatches[matched - 1];
          matched = partialMatches[matched - 1];
        }
      }

    }

    return ret;
  }

  private static void input() throws IOException {
    t = br.readLine();
    p = br.readLine();
  }

  private static void output(List<Integer> ans) throws IOException {
    bw.write(String.valueOf(count));
    bw.newLine();
    for (int idx : ans) {
      bw.write(String.valueOf(idx + 1));
      bw.write(' ');
    }
  }

}
