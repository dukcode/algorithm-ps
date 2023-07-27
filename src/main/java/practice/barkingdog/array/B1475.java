package practice.barkingdog.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1475 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int[] cnt = new int[10];

  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new FileReader("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int num = Integer.parseInt(br.readLine());
    while (num > 0) {
      cnt[num % 10]++;
      num /= 10;
    }

    int avg = (cnt[6] + cnt[9] + 1) / 2;
    cnt[6] = avg;
    cnt[9] = avg;

    int ret = 0;
    for (int i = 0; i < 10; ++i) {
      ret = Math.max(ret, cnt[i]);
    }

    bw.write(String.valueOf(ret));

    br.close();
    bw.close();
  }


}
