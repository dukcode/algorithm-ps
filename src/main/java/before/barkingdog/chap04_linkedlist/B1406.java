package before.barkingdog.chap04_linkedlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class B1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        LinkedList<Character> ll = new LinkedList<>();
        for (int i = 0; i < str.length(); ++i) {
            ll.add(str.charAt(i));
        }
        ListIterator<Character> it = ll.listIterator(ll.size());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String ch = command.equals("P") ? st.nextToken() : "";

            switch (command) {
                case "L":
                    if (it.hasPrevious()) {
                        it.previous();
                    }
                    break;
                case "D":
                    if (it.hasNext()) {
                        it.next();
                    }
                    break;
                case "B":
                    if (it.hasPrevious()) {
                        it.previous();
                        it.remove();
                    }
                    break;
                case "P":
                    it.add(ch.charAt(0));
                    break;
                default:
                    break;
            }
        }

        for (Character c : ll) {
            bw.write(c);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
