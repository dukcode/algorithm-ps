package before.barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class B2910 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int c;

    private static class NumInfo {

        int idx;
        int count;

        public NumInfo(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }

    private static Map<Integer, NumInfo> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());

            if (!map.containsKey(num)) {
                map.put(num, new NumInfo(i, 1));
                continue;
            }

            NumInfo numInfo = map.get(num);
            numInfo.count++;
        }
        List<Entry<Integer, NumInfo>> list = new ArrayList<>(map.entrySet());

        list.sort((e1, e2) -> {
            if (e2.getValue().count == e1.getValue().count) {
                return e1.getValue().idx - e2.getValue().idx;
            }

            return e2.getValue().count - e1.getValue().count;
        });

        for (Entry<Integer, NumInfo> entry : list) {
            for (int i = 0; i < entry.getValue().count; ++i) {
                bw.write(String.valueOf(entry.getKey()));
                bw.write(' ');
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
