package barkingdog.chap03_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * B1919
 */
public class B1919 {

    private static int[] arr = new int[26];

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = br.readLine();
        String b = br.readLine();

        for (int i = 0; i < a.length(); ++i) {
            arr[a.charAt(i) - 'a']++;
        }

        for (int i = 0; i < b.length(); ++i) {
            arr[b.charAt(i) - 'a']--;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans += Math.abs(arr[i]);
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}


