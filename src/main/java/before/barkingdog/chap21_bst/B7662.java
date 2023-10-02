package before.barkingdog.chap21_bst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class B7662 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int t;
    private static int k;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                switch (command) {
                    case "I":
                        map.put(num, map.getOrDefault(num, 0) + 1);
                        break;
                    case "D":
                        if (map.size() == 0) {
                            continue;
                        }

                        num = num == 1 ? map.lastKey() : map.firstKey();
                        if (map.put(num, map.get(num) - 1) == 1) {
                            map.remove(num);
                        }
                        break;
                    default:
                        break;
                }
            }

            bw.write(map.size() == 0 ? "EMPTY" : map.lastKey() + " " + map.firstKey());
            bw.newLine();
        }

        bw.close();
        br.close();
    }

}
