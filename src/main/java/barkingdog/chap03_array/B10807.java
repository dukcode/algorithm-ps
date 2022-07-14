package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**
 * B10807
 */
public class B10807 {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = Integer.valueOf(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.valueOf(br.readLine());
        int ans = 0;
        for (int i = 0; i < cnt; ++i) {
            if (num == Integer.valueOf(st.nextToken())) {
                ans++;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}


