package barkingdog.chap02_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * B10869
 */
public class B10869 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(a + b));
        bw.newLine();
        bw.write(String.valueOf(a - b));
        bw.newLine();
        bw.write(String.valueOf(a * b));
        bw.newLine();
        bw.write(String.valueOf(a / b));
        bw.newLine();
        bw.write(String.valueOf(a % b));
        bw.newLine();

        bw.flush();
        bw.close();
    }
}
