package jongman.chap21_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Fortress {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[] x;
  private static int[] y;
  private static int[] r;

  private static int longest;

  private static class TreeNode {

    List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode child) {
      children.add(child);
    }

    public List<TreeNode> getChildren() {
      return this.children;
    }
  }

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      x = new int[n];
      y = new int[n];
      r = new int[n];

      for (int i = 0; i < n; ++i) {
        st = new StringTokenizer(br.readLine());
        x[i] = Integer.parseInt(st.nextToken());
        y[i] = Integer.parseInt(st.nextToken());
        r[i] = Integer.parseInt(st.nextToken());
      }

      TreeNode root = getTree(0);

      longest = 0;
      int height = height(root);

      bw.write(String.valueOf(Math.max(height, longest)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int height(TreeNode root) {

    List<Integer> heights = new ArrayList<>();
    for (TreeNode child : root.children) {
      heights.add(height(child));
    }

    if (heights.isEmpty()) {
      return 0;
    }

    Collections.sort(heights);

    int n = heights.size();
    if (n >= 2) {
      longest = Math.max(longest, 2 + heights.get(n - 1) + heights.get(n - 2));
    }

    return heights.get(n - 1) + 1;
  }

  public static TreeNode getTree(int parent) {
    TreeNode ret = new TreeNode();
    for (int ch = 0; ch < n; ++ch) {
      if (isChild(parent, ch)) {
        ret.add(getTree(ch));
      }
    }

    return ret;
  }

  private static boolean isChild(int parent, int child) {
    if (!enclose(parent, child)) {
      return false;
    }

    for (int i = 0; i < n; ++i) {
      if (i != parent && i != child && enclose(parent, i) && enclose(i, child)) {
        return false;
      }
    }

    return true;
  }

  private static boolean enclose(int outer, int inner) {
    return r[outer] > r[inner] && squareDist(outer, inner) < square(r[outer] - r[inner]);
  }

  private static int squareDist(int outer, int inner) {
    return square(x[outer] - x[inner]) + square(y[outer] - y[inner]);
  }


  private static int square(int x) {
    return x * x;
  }


}
