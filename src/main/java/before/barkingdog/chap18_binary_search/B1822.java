package before.barkingdog.chap18_binary_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B1822 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int[] a;
    private static int m;
    private static Set<Integer> b = new HashSet<>();


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(a);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!b.contains(a[i])) {
                ans.add(a[i]);
            }
        }

        bw.write(String.valueOf(ans.size()));
        bw.newLine();
        for (int i = 0; i < ans.size(); i++) {
            bw.write(String.valueOf(ans.get(i)));
            bw.write(' ');
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
