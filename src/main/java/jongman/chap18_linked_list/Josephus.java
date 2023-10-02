package jongman.chap18_linked_list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href=https://www.algospot.com/judge/problem/read/JOSEPHUS>URL</a>
 */
public class Josephus {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int k;

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());

    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());

      LinkedList<Integer> ll = IntStream.range(0, n).boxed()
          .collect(Collectors.toCollection(LinkedList::new));

      Iterator<Integer> it = ll.iterator();
      while (n > 2) {
        if (!it.hasNext()) {
          it = ll.iterator();
        }
        it.next();
        it.remove();
        n--;

        for (int i = 0; i < k - 1; ++i) {
          if (!it.hasNext()) {
            it = ll.iterator();
          }
          it.next();
        }
      }

      bw.write(String.valueOf(ll.getFirst() + 1));
      bw.write(' ');
      bw.write(String.valueOf(ll.getLast() + 1));
      bw.newLine();
    }

    br.close();
    bw.close();
  }


}
