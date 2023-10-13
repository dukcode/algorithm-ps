import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchTest() throws Exception {
    // given
    int[] arr = {0, 0, 2, 2, 4};

    // when

    for (int i = 0; i <= 4; ++i) {
      System.out.println("find " + i + " : " + Arrays.binarySearch(arr, i));
    }
  }

  @Test
  public void lowerBoundTest() throws Exception {
    // given
    int[] arr = {0, 0, 2, 2, 4};

    // when
    assertThat(lowerBound(arr, 0)).isEqualTo(0);
    assertThat(lowerBound(arr, 2)).isEqualTo(2);
    assertThat(lowerBound(arr, 1)).isEqualTo(2);
  }

  @Test
  public void upperBoundTest() throws Exception {
    // given
    int[] arr = {0, 0, 2, 2, 4};

    // when
    assertThat(upperBound(arr, 0)).isEqualTo(2);
    assertThat(upperBound(arr, 1)).isEqualTo(2);
    assertThat(upperBound(arr, 2)).isEqualTo(4);
  }

  @Test
  public void upperLowerBound2Test() throws Exception {
    // given
    int[] arr = {0, 0, 2, 2, 4};

    // when
    assertThat(upperBound(arr, 0)).isEqualTo(upperBound2(arr, 0));
    assertThat(upperBound(arr, 1)).isEqualTo(upperBound2(arr, 1));
    assertThat(upperBound(arr, 2)).isEqualTo(upperBound2(arr, 2));

    assertThat(lowerBound(arr, 0)).isEqualTo(lowerBound2(arr, 0));
    assertThat(lowerBound(arr, 1)).isEqualTo(lowerBound(arr, 1));
    assertThat(lowerBound(arr, 2)).isEqualTo(lowerBound2(arr, 2));

  }

  int lowerBound(int[] arr, int target) {
    return lowerBound(arr, 0, arr.length, target);
  }

  int lowerBound(int[] arr, int fromIdx, int toIdx, int target) {
    int l = fromIdx;
    int r = toIdx;
    while (l + 1 < r) {
      int mid = (l + r - 1) / 2; // 010 , 0010
      if (arr[mid] >= target) {
        r = mid + 1;
      } else {
        l = mid + 1;
      }
    }
    return l;
  }

  int upperBound(int[] arr, int target) {
    return upperBound(arr, 0, arr.length, target);
  }

  int upperBound(int[] arr, int fromIdx, int toIdx, int target) {
    int l = fromIdx;
    int r = toIdx;

    while (l + 1 < r) {
      int mid = (l + r - 1) / 2;
      if (arr[mid] > target) {
        r = mid + 1;
      } else {
        l = mid + 1;
      }
    }

    return l;
  }

  int upperBound2(int[] arr, int target) {
    int ret = Arrays.binarySearch(arr, target + 1);
    return ret < 0 ? -(ret + 1) : ret;
  }

  int lowerBound2(int[] arr, int target) {
    int ret = Arrays.binarySearch(arr, target);
    return ret < 0 ? -(ret + 1) : ret;
  }

}
