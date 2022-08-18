package review.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B6549 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    private static class Block {

        long h;
        long idx;

        public Block(long h, long idx) {
            this.h = h;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line.strip().equals("0")) {
                break;
            }

            long ans = 0;

            StringTokenizer st = new StringTokenizer(line);

            n = Integer.parseInt(st.nextToken());

            Stack<Block> stk = new Stack<>();
            for (int i = 0; i < n; ++i) {
                int num = Integer.parseInt(st.nextToken());

                long idx = i;
                while (!stk.isEmpty() && stk.peek().h >= num) {
                    long h = stk.peek().h;
                    idx = stk.peek().idx;
                    ans = Math.max(ans, (i - idx) * h);

                    stk.pop();
                }

                stk.push(new Block(num, idx));
            }

            for (Block block : stk) {
                ans = Math.max(ans, (n - block.idx) * block.h);
            }

            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
