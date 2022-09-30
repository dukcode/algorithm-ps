package barkingdog.chap19_hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B19583 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static class Time {

        int h;
        int m;

        public Time(String time) {
            String[] tokens = time.split(":");
            this.h = Integer.parseInt(tokens[0]);
            this.m = Integer.parseInt(tokens[1]);
        }

        public int compare(Time t) {
            if (this.h == t.h) {
                return this.m - t.m;
            }

            return this.h - t.h;
        }
    }

    private static Time s;
    private static Time e;
    private static Time q;

    private static Set<String> entranceSet = new HashSet<>();
    private static Set<String> quitSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        s = new Time(st.nextToken());
        e = new Time(st.nextToken());
        q = new Time(st.nextToken());

        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            Time time = new Time(st.nextToken());
            String nickname = st.nextToken();

            if (time.compare(s) <= 0) {
                entranceSet.add(nickname);
            } else if (time.compare(e) >= 0 && time.compare(q) <= 0) {
                quitSet.add(nickname);
            }
        }

        int cnt = 0;
        for (String nickname : entranceSet) {
            if (quitSet.contains(nickname)) {
                cnt++;
            }
        }

        bw.write(String.valueOf(cnt));

        bw.flush();
        bw.close();
        br.close();
    }

}
