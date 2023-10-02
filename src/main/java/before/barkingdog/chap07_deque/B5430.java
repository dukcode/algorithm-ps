package before.barkingdog.chap07_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class B5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        Loop:
        while (T-- > 0) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());
            LinkedList<Integer> d = parseDeque(br.readLine());

            boolean isForward = true;
            for (int i = 0; i < commands.length(); ++i) {
                char command = commands.charAt(i);

                if (command == 'R') {
                    isForward = !isForward;
                    continue;
                }

                if (command == 'D') {
                    if (d.isEmpty()) {
                        bw.write("error");
                        bw.newLine();
                        continue Loop;
                    }
                    if (isForward) {
                        d.pollFirst();
                    } else {
                        d.pollLast();
                    }
                }
            }

            StringJoiner sj = new StringJoiner(",", "[", "]");
            Iterator<Integer> it =
                    isForward ? d.listIterator() : d.descendingIterator();

            while (it.hasNext()) {
                sj.add(String.valueOf(it.next()));
            }
            bw.write(sj.toString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static LinkedList<Integer> parseDeque(String src) {
        int size = src.length();
        StringTokenizer st = new StringTokenizer(src.substring(1, size - 1), ",");

        LinkedList<Integer> ret = new LinkedList<>();
        while (st.hasMoreTokens()) {
            ret.add(Integer.parseInt(st.nextToken()));
        }

        return ret;
    }


}
