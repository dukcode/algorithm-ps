package before.barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2493 {

    public static class Tower {

        public int idx;
        public int height;

        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Tower> stk = new Stack<>();
        stk.push(new Tower(0, Integer.MAX_VALUE));

        for (int i = 1; i <= N; ++i) {
            int height = Integer.parseInt(st.nextToken());

            while (stk.peek().height < height) {
                stk.pop();
            }

            bw.write(String.valueOf(stk.peek().idx));
            bw.write(" ");
            stk.push(new Tower(i, height));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
