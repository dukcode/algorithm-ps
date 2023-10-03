import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class KmpTest {

  @Test
  public void kmpTest() {
    String s = "aabaabac";

    int[] pm1 = calPm1(s);
    int[] pm2 = calPm2(s);
    Assertions.assertThat(pm1).containsExactly(pm2);
  }

  public int[] calPm1(String s) {
    int n = s.length();
    int[] ret = new int[n];

    int matched = 0;
    for (int i = 1; i < n; ++i) {
      while (matched > 0 && s.charAt(i) != s.charAt(matched)) {
        matched = ret[matched - 1];
      }

      if (s.charAt(i) == s.charAt(matched)) {
        matched++;
        ret[i] = matched;
      }
    }

    return ret;
  }

  public int[] calPm2(String s) {
    int n = s.length();
    int[] ret = new int[n];

    int begin = 1;
    int matched = 0;
    while (begin + matched < n) {
      if (s.charAt(matched) == s.charAt(begin + matched)) {
        matched++;
        ret[begin + matched - 1] = matched;
      } else {
        if (matched == 0) {
          begin++;
        } else {
          begin += matched - ret[matched - 1];
          matched = ret[matched - 1];
        }
      }
    }

    return ret;
  }

}
