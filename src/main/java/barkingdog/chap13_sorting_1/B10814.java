package barkingdog.chap13_sorting_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// stable sort
public class B10814 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;

    private static People[] peoples;

    private static class People {

        int age;
        String name;

        public People(int age, String name) {
            this.age = age;
            this.name = name;
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        peoples = new People[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());

            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            peoples[i] = new People(age, name);
        }

        Arrays.sort(peoples, (p1, p2) -> p1.age - p2.age);

        for (int i = 0; i < n; ++i) {
            bw.write(String.valueOf(peoples[i].age));
            bw.write(' ');
            bw.write(peoples[i].name);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
