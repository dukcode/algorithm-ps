package before.barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B9375 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int n;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String category = st.nextToken();
                if (map.containsKey(category)) {
                    map.put(category, map.get(category) + 1);
                } else {
                    map.put(category, 1);
                }
            }

            int ans = 1;
            for (String category : map.keySet()) {
                ans *= map.get(category) + 1;
            }
            ans--;

            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
