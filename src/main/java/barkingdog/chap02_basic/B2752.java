package barkingdog.chap02_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * B2752
 */
public class B2752 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        List<Integer> list = Arrays.asList(a, b, c);
        Collections.sort(list);
        for (int num : list) {
            bw.write(String.valueOf(num));
            bw.write(" ");
        }

        bw.flush();
        bw.close();
    }
}
