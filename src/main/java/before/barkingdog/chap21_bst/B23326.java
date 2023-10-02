package before.barkingdog.chap21_bst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B23326 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int q;
    private static TreeSet<Integer> hu = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                hu.add(i);
            }
        }

        int cur = 1;
        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int num = 0;

            switch (command) {
                case 1:
                    num = Integer.parseInt(st.nextToken());
                    if (hu.contains(num)) {
                        hu.remove(num);
                    } else {
                        hu.add(num);
                    }
                    break;
                case 2:
                    num = Integer.parseInt(st.nextToken());
                    cur = (cur + num - 1) % n + 1;
                    break;
                case 3:
                    if (hu.isEmpty()) {
                        bw.write(String.valueOf(-1));
                    } else {
                        Integer next = hu.ceiling(cur);
                        next = next == null ? hu.first() : next;

                        if (cur <= next) {
                            bw.write(String.valueOf(next - cur));
                        } else {
                            bw.write(String.valueOf(n - cur + next));
                        }
                    }
                    bw.newLine();
                    break;
                default:
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
