package before.barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B13335 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static int w;
    private static int l;

    private static List<Integer> trucks = new ArrayList<>();

    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        for (int i = 0; i < w; ++i) {
            trucks.add(0);
        }
        int startIdx = 0;
        int weightSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int weight = Integer.parseInt(st.nextToken());

            weightSum -= trucks.get(startIdx++);

            while (weightSum + weight > l) {
                weightSum -= trucks.get(startIdx++);
                trucks.add(0);
            }

            trucks.add(weight);
            weightSum += weight;
        }
        bw.write(String.valueOf(trucks.size()));

        bw.flush();
        bw.close();
        br.close();
    }

}
