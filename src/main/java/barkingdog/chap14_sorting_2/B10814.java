package barkingdog.chap14_sorting_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10814 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n;
    private static Person[] people;

    private static class Person {

        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        people = new Person[n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            people[i] = new Person(age, name);
        }

        Arrays.sort(people, (p1, p2) -> p1.age - p2.age);

        for (int i = 0; i < n; ++i) {
            bw.write(String.valueOf(people[i].age));
            bw.write(' ');
            bw.write(people[i].name);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
