package jongman.chap21_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Traversal {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static List<Integer> preorder;
  private static List<Integer> inorder;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      input();
      System.out.println(postorder(preorder, inorder));
    }

    br.close();
    bw.close();
  }

  private static List<Integer> postorder(List<Integer> preorder, List<Integer> inorder) {
    int n = preorder.size();

    if (n == 0) {
      return new ArrayList<>();
    }

    int root = preorder.get(0);
    int nextSize = inorder.indexOf(root);

    List<Integer> leftPostOrder = postorder(
        preorder.subList(1, nextSize + 1),
        inorder.subList(0, nextSize));
    List<Integer> rightPostOrder = postorder(
        preorder.subList(nextSize + 1, n),
        inorder.subList(nextSize + 1, n));

    List<Integer> ret = new ArrayList<>();
    ret.addAll(leftPostOrder);
    ret.addAll(rightPostOrder);
    ret.add(root);
    return ret;
  }

  private static void input() throws IOException {
    n = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    preorder = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      preorder.add(Integer.parseInt(st.nextToken()));
    }

    st = new StringTokenizer(br.readLine());
    inorder = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      inorder.add(Integer.parseInt(st.nextToken()));
    }
  }


}
