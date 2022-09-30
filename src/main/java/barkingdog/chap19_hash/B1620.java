package barkingdog.chap19_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class B1620 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;
    private static String[] arr;
    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new String[n];
        for (int idx = 0; idx < n; idx++) {
            String name = br.readLine();
            arr[idx] = name;
            map.put(name, idx);
        }

        for (int i = 0; i < m; ++i) {
            String q = br.readLine();
            if (map.containsKey(q)) {
                bw.write(String.valueOf(map.get(q) + 1));
            } else {
                bw.write(arr[Integer.parseInt(q) - 1]);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
