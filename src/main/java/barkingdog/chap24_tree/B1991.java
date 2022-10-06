package barkingdog.chap24_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B1991 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static int[] lc;
    private static int[] rc;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = Arrays.stream(new int[]{1, 2, 3}).boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        q.stream().mapToInt(i -> i).sum();

        n = Integer.parseInt(br.readLine());
        lc = new int[n + 1];
        rc = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A' + 1;

            char l = st.nextToken().charAt(0);
            lc[parent] = l == '.' ? 0 : l - 'A' + 1;

            char r = st.nextToken().charAt(0);
            rc[parent] = r == '.' ? 0 : r - 'A' + 1;
        }

        preorder(1);
        bw.newLine();
        inorder(1);
        bw.newLine();
        postorder(1);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void preorder(int idx) throws IOException {
        bw.write(idx - 1 + 'A');

        if (lc[idx] != 0) {
            preorder(lc[idx]);
        }

        if (rc[idx] != 0) {
            preorder(rc[idx]);
        }

    }

    private static void inorder(int idx) throws IOException {
        if (lc[idx] != 0) {
            inorder(lc[idx]);
        }

        bw.write(idx - 1 + 'A');

        if (rc[idx] != 0) {
            inorder(rc[idx]);
        }
    }

    private static void postorder(int idx) throws IOException {
        if (lc[idx] != 0) {
            postorder(lc[idx]);
        }

        if (rc[idx] != 0) {
            postorder(rc[idx]);
        }

        bw.write(idx - 1 + 'A');
    }

}
