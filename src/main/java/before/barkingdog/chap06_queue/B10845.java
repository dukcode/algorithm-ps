package before.barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B10845 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        int back = 0;
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    back = Integer.parseInt(st.nextToken());
                    q.offer(back);
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        bw.write("-1");
                        bw.newLine();
                        break;
                    }
                    bw.write(String.valueOf(q.poll()));
                    bw.newLine();
                    break;
                case "size":
                    bw.write(String.valueOf(q.size()));
                    bw.newLine();
                    break;
                case "empty":
                    if (q.isEmpty()) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                    bw.newLine();
                    break;
                case "front":
                    if (q.isEmpty()) {
                        bw.write("-1");
                        bw.newLine();
                        break;
                    }
                    bw.write(String.valueOf(q.peek()));
                    bw.newLine();
                    break;
                case "back":
                    if (q.isEmpty()) {
                        bw.write("-1");
                        bw.newLine();
                        break;
                    }
                    bw.write(String.valueOf(back));
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
