package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * B1000
 */
public class B1000 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int firstNum;
    private static int secondNum;

    public static void main(String[] args) {

        try {
            input();
            solve();

            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        firstNum = Integer.valueOf(st.nextToken());
        secondNum = Integer.valueOf(st.nextToken());
    }

    public static void solve() throws IOException {
        bw.write(String.valueOf(firstNum + secondNum));
    }
}
