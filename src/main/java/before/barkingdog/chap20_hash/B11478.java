package before.barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class B11478 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String str;
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();

        for (int st = 0; st < str.length(); ++st) {
            for (int en = st + 1; en <= str.length(); ++en) {
                String substring = str.substring(st, en);
                set.add(substring);
            }
        }

        bw.write(String.valueOf(set.size()));

        bw.flush();
        bw.close();
        br.close();
    }

}
