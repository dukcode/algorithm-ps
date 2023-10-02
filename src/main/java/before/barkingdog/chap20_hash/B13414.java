package before.barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class B13414 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int k;
    private static int l;

    private static LinkedHashSet<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        for (int i = 0; i < l; ++i) {
            String id = br.readLine();
            set.remove(id);
            set.add(id);
        }

        Iterator<String> it = set.iterator();
        for (int i = 0; i < k; ++i) {
            if (!it.hasNext()) {
                break;
            }
            bw.write(it.next());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
