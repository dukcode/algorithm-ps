package practice.barkingdog.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2577 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int a;
  private static int b;
  private static int c;

  private static int[] cnt = new int[10];

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    a = Integer.parseInt(br.readLine());
    b = Integer.parseInt(br.readLine());
    c = Integer.parseInt(br.readLine());

    int product = a * b * c;
    while (product > 0) {
      cnt[product % 10]++;
      product /= 10;
    }

    for (int i = 0; i < 10; ++i) {
      int n = cnt[i];
      bw.write(String.valueOf(n));
      bw.newLine();
    }

    bw.close();
    br.close();
  }
}
