package barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B10866 {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            int value = 0;
            switch (command) {
                case "push_front":
                    value = Integer.parseInt(st.nextToken());
                    dq.addFirst(value);
                    break;
                case "push_back":
                    value = Integer.parseInt(st.nextToken());
                    dq.addLast(value);
                    break;
                case "pop_front":
                    value = dq.isEmpty() ? -1 : dq.pollFirst();
                    bw.write(String.valueOf(value));
                    bw.newLine();
                    break;
                case "pop_back":
                    value = dq.isEmpty() ? -1 : dq.pollLast();
                    bw.write(String.valueOf(value));
                    bw.newLine();
                    break;
                case "size":
                    value = dq.size();
                    bw.write(String.valueOf(value));
                    bw.newLine();
                    break;
                case "empty":
                    if (dq.isEmpty()) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                    bw.newLine();
                    break;
                case "front":
                    value = dq.isEmpty() ? -1 : dq.getFirst();
                    bw.write(String.valueOf(value));
                    bw.newLine();
                    break;
                case "back":
                    value = dq.isEmpty() ? -1 : dq.getLast();
                    bw.write(String.valueOf(value));
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
