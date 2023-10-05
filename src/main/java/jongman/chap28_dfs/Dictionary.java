package jongman.chap28_dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static String[] words;

  private static boolean[][] adj;
  private static boolean[] visited;

  private static final int ALPHABET_COUNT = 26;

  private static final String FAIL_RESULT = "INVALID HYPOTHESIS";


  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      input();

      String ans = solve();

      bw.write(ans);
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static String solve() {
    makeGraph();
    List<Integer> order = topologicalSort();

    if (order.isEmpty()) {
      return FAIL_RESULT;
    }

    StringBuilder sb = new StringBuilder();
    for (int idx : order) {
      sb.append((char) (idx + 'a'));
    }
    return sb.toString();
  }

  private static List<Integer> topologicalSort() {
    visited = new boolean[ALPHABET_COUNT];
    List<Integer> order = new ArrayList<>();

    for (int i = ALPHABET_COUNT - 1; i >= 0; --i) {
      if (visited[i]) {
        continue;
      }
      dfs(i, order);
    }

    Collections.reverse(order);

    for (int here = 0; here < ALPHABET_COUNT; ++here) {
      for (int there = here + 1; there < ALPHABET_COUNT; ++there) {
        if (adj[order.get(there)][order.get(here)]) {
          return Collections.emptyList();
        }
      }
    }

    return order;
  }

  private static void dfs(int here, List<Integer> order) {
    visited[here] = true;
    for (int there = 0; there < ALPHABET_COUNT; ++there) {
      if (visited[there]) {
        continue;
      }

      if (adj[here][there]) {
        dfs(there, order);
      }
    }

    order.add(here);
  }

  private static void makeGraph() {
    adj = new boolean[ALPHABET_COUNT][ALPHABET_COUNT];
    for (int i = 1; i < n; ++i) {
      String wordA = words[i - 1];
      String wordB = words[i];

      int length = Math.min(wordA.length(), wordB.length());
      for (int idx = 0; idx < length; ++idx) {
        if (wordA.charAt(idx) == wordB.charAt(idx)) {
          continue;
        }

        adj[wordA.charAt(idx) - 'a'][wordB.charAt(idx) - 'a'] = true;
        break;
      }
    }
  }

  private static void input() throws IOException {
    n = Integer.parseInt(br.readLine());

    words = new String[n];
    for (int i = 0; i < n; ++i) {
      words[i] = br.readLine();
    }
  }


}
