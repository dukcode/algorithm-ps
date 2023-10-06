package jongman.chap28_dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class WordChain {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;


  private static int[][] adj;
  private static int[] outdegree;
  private static int[] indegree;
  private static Queue<String>[][] graph;

  private static final int COUNT_ALPHABET = 26;

  private static final int EULER_CIRCUIT = 1;
  private static final int NOTHING = 0;
  private static final int EULER_TRACE = -1;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      adj = new int[COUNT_ALPHABET][COUNT_ALPHABET];
      indegree = new int[COUNT_ALPHABET];
      outdegree = new int[COUNT_ALPHABET];
      graph = new ArrayDeque[COUNT_ALPHABET][COUNT_ALPHABET];
      for (int y = 0; y < COUNT_ALPHABET; y++) {
        for (int x = 0; x < COUNT_ALPHABET; x++) {
          graph[y][x] = new ArrayDeque<>();
        }
      }

      for (int i = 0; i < n; ++i) {
        String word = br.readLine();
        int length = word.length();

        char firstChar = word.charAt(0);
        char lastChar = word.charAt(length - 1);

        adj[firstChar - 'a'][lastChar - 'a']++;
        outdegree[firstChar - 'a']++;
        indegree[lastChar - 'a']++;
        graph[firstChar - 'a'][lastChar - 'a'].offer(word);
      }

      List<Integer> ans = solve();

      if (ans.isEmpty()) {
        bw.write("IMPOSSIBLE");
      } else {
        Collections.reverse(ans);
        for (int i = 1; i < ans.size(); ++i) {
          int l = ans.get(i - 1);
          int r = ans.get(i);
          bw.write(graph[l][r].poll());
          bw.write(' ');
        }
      }
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static List<Integer> solve() {

    int check = checkEuler();

    if (check == NOTHING) {
      return Collections.emptyList();
    }

    List<Integer> ret = new ArrayList<>();
    if (check == EULER_TRACE) {
      for (int i = 0; i < COUNT_ALPHABET; ++i) {
        if (outdegree[i] == indegree[i] + 1) {
          getEulerCircuit(i, ret);
          return ret;
        }
      }
    } else if (check == EULER_CIRCUIT) {
      for (int i = 0; i < COUNT_ALPHABET; ++i) {
        if (outdegree[i] != 0) {
          getEulerCircuit(i, ret);
          return ret;
        }
      }
    }

    return ret;
  }

  private static void getEulerCircuit(int here, List<Integer> circuit) {
    for (int there = 0; there < COUNT_ALPHABET; ++there) {
      if (adj[here][there] != 0) {
        adj[here][there]--;
        getEulerCircuit(there, circuit);
      }
    }
    circuit.add(here);
  }


  private static int checkEuler() {
    int plus1 = 0;
    int minus1 = 0;

    for (int i = 0; i < COUNT_ALPHABET; ++i) {
      int delta = outdegree[i] - indegree[i];

      if (delta < -1 || 1 < delta) {
        return NOTHING;
      }

      if (delta == 1) {
        plus1++;
      }
      if (delta == -1) {
        minus1++;
      }
    }

    if (plus1 == 1 && minus1 == 1) {
      return EULER_TRACE;
    }

    if (plus1 == 0 && minus1 == 0) {
      return EULER_CIRCUIT;
    }

    return NOTHING;
  }


}
