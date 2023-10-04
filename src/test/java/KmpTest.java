import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class KmpTest {

  @Test
  public void kmpTest() {
    String text = "asdfasdfasdfasdfsadffsadasdfsadfsadfsadffsadasdffasdfasdafasf";
    String pattern = "asdfasdf";

    List<Integer> result = find2(text, pattern);
    char[] visualization = new char[text.length()];
    Arrays.fill(visualization, ' ');
    for (int idx : result) {
      for (int i = idx; i < idx + pattern.length(); ++i) {
        visualization[i] = '_';
      }
    }
    for (int idx : result) {
      visualization[idx] = '!';
    }

    System.out.println(text);
    System.out.println(visualization);
  }

  private static List<Integer> find(String text, String pattern) {
    int[] pm = calPartialMatches(pattern);
    List<Integer> ret = new ArrayList<>();
    int m = text.length();
    int n = pattern.length();
    int begin = 0;
    int matched = 0;
    while (begin < m - n) {
      if (matched < n && text.charAt(begin + matched) == pattern.charAt(matched)) {
        matched++;
        if (matched == n) {
          ret.add(begin);
        }
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - pm[matched - 1];
          matched = pm[matched - 1];
        }
      }
    }
    return ret;
  }

  private static int[] calPartialMatches(String pattern) {
    int n = pattern.length();
    int[] pm = new int[n];

    int begin = 1;
    int matched = 0;
    while (begin + matched < n) {
      if (pattern.charAt(begin + matched) == pattern.charAt(matched)) {
        matched++;
        pm[begin + matched - 1] = matched;
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - pm[matched - 1];
          matched = pm[matched - 1];
        }
      }
    }
    return pm;
  }

  private static List<Integer> find2(String text, String pattern) {
    int[] pm = calPartialMatches2(pattern);
    List<Integer> ret = new ArrayList<>();
    int m = text.length();
    int n = pattern.length();

    int matched = 0;
    for (int i = 0; i < m; ++i) {
      while (matched > 0 && pattern.charAt(matched) != text.charAt(i)) {
        matched = pm[matched - 1];
      }

      if (pattern.charAt(matched) == text.charAt(i)) {
        matched++;
        if (matched == n) {
          ret.add(i - n + 1);
          matched = pm[matched - 1];
        }
      }
    }

    return ret;
  }

  private static int[] calPartialMatches2(String pattern) {
    int n = pattern.length();
    int[] pm = new int[n];

    int matched = 0;
    for (int i = 1; i < n; ++i) {
      while (matched > 0 && pattern.charAt(matched) != pattern.charAt(i)) {
        matched = pm[matched - 1];
      }

      if (pattern.charAt(matched) == pattern.charAt(i)) {
        matched++;
        pm[i] = matched;
      }
    }

    System.out.println(Arrays.toString(pm));
    return pm;
  }
}
