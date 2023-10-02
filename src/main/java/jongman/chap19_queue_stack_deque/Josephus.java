package jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
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

      Queue<Integer> q = IntStream.range(0, n).boxed()
          .collect(Collectors.toCollection(LinkedList::new));

      while (q.size() > 2) {
        q.poll();
        for (int i = 0; i < k - 1; ++i) {
          q.offer(q.poll());
        }
      }

      bw.write(String.valueOf(q.poll() + 1));
      bw.write(' ');
      bw.write(String.valueOf(q.poll() + 1));
      bw.newLine();
    }

    br.close();
    bw.close();
  }


}
