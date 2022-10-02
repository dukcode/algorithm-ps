package barkingdog.chap20_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class B16165 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int m;

    private static Map<String, List<String>> group2name = new HashMap<>();
    private static Map<String, String> name2group = new HashMap<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; ++i) {
            String groupName = br.readLine();
            int numMember = Integer.parseInt(br.readLine());
            for (int j = 0; j < numMember; j++) {
                String name = br.readLine();

                name2group.put(name, groupName);

                if (!group2name.containsKey(groupName)) {
                    group2name.put(groupName, new ArrayList<>());
                }

                List<String> list = group2name.get(groupName);
                list.add(name);
            }
        }

        for (String group : group2name.keySet()) {
            List<String> members = group2name.get(group);
            Collections.sort(members);
        }

        for (int i = 0; i < m; i++) {
            String quiz = br.readLine();
            int category = Integer.parseInt(br.readLine());

            if (category == 0) {
                List<String> members = group2name.get(quiz);
                for (String member : members) {
                    bw.write(member);
                    bw.newLine();
                }
            } else {
                String group = name2group.get(quiz);
                bw.write(group);
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
