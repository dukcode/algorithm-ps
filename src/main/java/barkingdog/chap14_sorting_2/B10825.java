package barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10825 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static Score[] scores;

    private static class Score implements Comparable<Score> {

        String name;
        int korean;
        int english;
        int math;

        public Score(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Score o) {

            if (this.korean == o.korean &&
                    this.english == o.english
                    && this.math == o.math) {
                return this.name.compareTo(o.name);
            }

            if (this.korean == o.korean && this.english == o.english) {
                return o.math - this.math;
            }

            if (this.korean == o.korean) {
                return this.english - o.english;
            }

            return o.korean - this.korean;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        scores = new Score[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            scores[i] = new Score(name, korean, english, math);
        }

        Arrays.sort(scores);

        for (int i = 0; i < n; i++) {
            bw.write(scores[i].name);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
