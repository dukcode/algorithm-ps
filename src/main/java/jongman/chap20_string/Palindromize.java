package jongman.chap20_string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Palindromize {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static String str;

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    str = br.readLine();
    String reverseStr = new StringBuilder(str).reverse().toString();

    int ans = 2 * str.length() - solve(str, reverseStr);

    bw.write(String.valueOf(ans));

    br.close();
    bw.close();
  }

  // 일치하는 부분 길이 반환
  private static int solve(String str, String reverseStr) {
    int m = str.length();

    int[] pm = calculatePartialMatches(reverseStr);

    int begin = 0;
    int matched = 0;
    while (begin + matched < m) {
      if (str.charAt(begin + matched) == reverseStr.charAt(matched)) {
        matched++;
        if (begin + matched == m) {
          return matched;
        }
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - pm[matched - 1];
          matched = pm[matched - 1];
        }
      }
    }

    return 0;
  }

  private static int[] calculatePartialMatches(String s) {
    int n = s.length();
    int[] ret = new int[n];

    int begin = 1;
    int matched = 0;
    while (begin + matched < n) {
      if (s.charAt(matched) == s.charAt(begin + matched)) {
        matched++;
        ret[begin + matched - 1] = matched;
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - ret[matched - 1];
          matched = ret[matched - 1];
        }
      }
    }

    return ret;
  }

}
