package before.practice.barkingdog.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10808 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int[] cntAlphabet = new int[26];

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String str = br.readLine();
    for (char c : str.toCharArray()) {
      cntAlphabet[c - 'a']++;
    }

    for (int cnt : cntAlphabet) {
      bw.write(String.valueOf(cnt));
      bw.write(' ');
    }

    br.close();
    bw.close();
  }

}
