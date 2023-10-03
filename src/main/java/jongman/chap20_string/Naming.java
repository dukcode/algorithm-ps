package jongman.chap20_string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Naming {

  private static BufferedReader br;
  private static BufferedWriter bw;

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String s = br.readLine();
    int[] partialMatches = calculatePartialMatches(s);

    List<Integer> ansList = new ArrayList<>();

    int size = s.length();
    while (size > 0) {
      ansList.add(size);
      size = partialMatches[size - 1];
    }

    for (int ans : ansList) {
      bw.write(String.valueOf(ans));
      bw.write(' ');
    }

    br.close();
    bw.close();
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
