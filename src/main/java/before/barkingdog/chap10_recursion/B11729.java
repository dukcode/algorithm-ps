package before.barkingdog.chap10_recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B11729 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // static BufferedReader br = new BufferedReader(new FileReader("input.txt"));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        bw.write(String.valueOf((1 << n) - 1));
        bw.newLine();
        hanoi(1, 3, n);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void hanoi(int start, int end, int n) throws IOException {
        // base case
        if (n == 1) {
            printMove(start, end);
            return;
        }

        hanoi(start, 6 - start - end, n - 1);
        printMove(start, end);
        hanoi(6 - start - end, end, n - 1);
    }

    private static void printMove(int start, int end) throws IOException {
        bw.write(String.valueOf(start));
        bw.write(' ');
        bw.write(String.valueOf(end));
        bw.newLine();
    }

}
