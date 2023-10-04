package jongman.chap22_binary_search_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Nerd2 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static TreeMap<Integer, Integer> tree; // q, p

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      int ans = 0;
      tree = new TreeMap<>();
      for (int i = 0; i < n; ++i) {
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        if (!isDominated(p, q)) {
          removeDominated(p, q);
          tree.put(p, q);
        }
        ans += tree.size();
      }

      bw.write(String.valueOf(ans));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static void removeDominated(int p, int q) {
    while (true) {
      Entry<Integer, Integer> lowerEntry = tree.lowerEntry(p);
      if (lowerEntry == null || lowerEntry.getValue() >= q) {
        return;
      }
      tree.remove(lowerEntry.getKey());
    }
  }

  private static boolean isDominated(int p, int q) {
    Entry<Integer, Integer> entry = tree.higherEntry(p);

    if (Objects.isNull(entry)) {
      return false;
    }

    return q < entry.getValue();
  }


}
