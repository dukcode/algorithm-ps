package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * B11328
 */
public class B11328 {

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.valueOf(br.readLine());

        Loop1:
        for (int cnt = 0; cnt < N; ++cnt) {
            int[] arr = new int[26];
            Arrays.fill(arr, 0);

            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            if (a.length() != b.length()) {
                bw.write("Impossible");
                bw.newLine();
                continue;
            }

            for (int i = 0; i < a.length(); ++i) {
                arr[a.charAt(i) - 'a']++;
                arr[b.charAt(i) - 'a']--;
            }

            for (int i = 0; i < 26; ++i) {
                if (arr[i] != 0) {
                    bw.write("Impossible");
                    bw.newLine();
                    continue Loop1;
                }
            }

            bw.write("Possible");
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}


