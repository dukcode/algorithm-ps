package practice.jongman.divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class QuadTree {

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int c;
  private static String line;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("input.txt"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      line = br.readLine();
      StringCharacterIterator it = new StringCharacterIterator(line, 0);
      bw.write(flip(it));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static String flip(StringCharacterIterator it) {
    char current = it.current();
    it.next();

    if (current == CharacterIterator.DONE) {
      return "";
    }

    if (current != 'x') {
      return String.valueOf(current);
    }

    String upperLeft = flip(it);
    String upperRight = flip(it);
    String lowerLeft = flip(it);
    String lowerRight = flip(it);

    return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
  }


}
