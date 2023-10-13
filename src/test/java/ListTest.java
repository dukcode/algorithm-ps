import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListTest {

  int[] arr;
  int n;

  int[] cache;

  @BeforeEach
  public void init() {

    n = 10;
    System.out.println("n = " + n);

    Random random = new Random();
    arr = new int[n];
    for (int i = 0; i < n; ++i) {
      arr[i] = random.nextInt(30);
    }

    System.out.println("arr = " + Arrays.toString(arr));
  }

  @Test
  public void test() throws Exception {
    cache = new int[n + 1];
//    assertThat(lisBottomUp()).isEqualTo(lisTopDown(-1) - 1);
    assertThat(lisBottomUp()).isEqualTo(listNlgN());
  }

  private int lisTopDown(int start) {

    cache[start + 1] = 1;
    for (int next = start + 1; next < n; ++next) {
      if (start < 0 || arr[start] < arr[next]) {
        cache[start + 1] = Math.max(cache[start + 1], lisTopDown(next) + 1);
      }
    }
    return cache[start + 1];
  }

  public int lisBottomUp() {

    int ret = 0;

    int[] cache = new int[n];

    for (int now = 0; now < n; ++now) {
      cache[now] = 1;
      for (int before = 0; before < now; ++before) {
        if (arr[before] < arr[now]) {
          cache[now] = Math.max(cache[now], cache[before] + 1);
        }
      }
      ret = Math.max(ret, cache[now]);
    }

    return ret;
  }

  public int listNlgN() {
    int[] lastElements = new int[n];
    Arrays.fill(lastElements, Integer.MAX_VALUE);
    int len = 0;
    for (int idx = 0; idx < n; ++idx) {
      int pos = Arrays.binarySearch(arr, 0, len, arr[idx]);
      pos = pos < 0 ? -(pos + 1) : pos;
      len += pos == len ? 1 : 0;
      lastElements[pos] = Math.max(lastElements[pos], arr[idx]);
    }

    return len;
  }
}
