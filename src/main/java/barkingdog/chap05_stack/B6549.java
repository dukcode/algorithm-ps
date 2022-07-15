package barkingdog.chap05_stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class B6549 {

    public static class Rectangle {

        int idx;
        int height;

        public Rectangle(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line.equals("0")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());

            long maxArea = -1;
            Stack<Rectangle> stk = new Stack<>();
            for (int i = 0; i <= N; ++i) {
                int height = i == N ? 0 : Integer.parseInt(st.nextToken());
                int idx = i;

                while (!stk.isEmpty() && stk.peek().height >= height) {
                    maxArea = Math.max(maxArea, (long) (i - stk.peek().idx) * stk.peek().height);
                    idx = stk.peek().idx;
                    stk.pop();
                }

                stk.push(new Rectangle(idx, height));
            }

            bw.write(String.valueOf(maxArea));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }


    /*
    접근법 : 해당 블럭의 height를 최대높이로 가지는 사각형의 최대값에 집중한다.
    해당 블럭의 height를 최대높이를 가지는 최대넓이의 사각형은
    왼쪽 idx : 블럭 왼쪽에서 최초로 해당 height를 뛰어 넘는 높이를 가진 블럭의 위치
    오른쪽 idx : 블럭 오른쪽에서 최초로 해당 height 보다 낮은 높이의 idx - 1 이다.

    따라서 상승하는 monotone stack을 만들어가며 pop 시킬 때 넓이를 계산한다.
     */

}
