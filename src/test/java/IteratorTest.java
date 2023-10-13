import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class IteratorTest {

  @Test
  public void iteratorTest1() {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

    ListIterator<Integer> lit = list.listIterator();

    // | 1   2   3   4   5   6   7
    //   1 | 2   3   4   5   6   7
    assertThat(lit.next()).isEqualTo(1);

    //   1 | 2   3   4   5   6   7
    //   1   2 | 3   4   5   6   7
    assertThat(lit.next()).isEqualTo(2);

    //   1   2 | 3   4   5   6   7
    //   1   2   3 | 4   5   6   7
    assertThat(lit.next()).isEqualTo(3);

    //   1   2   3 | 4   5   6   7
    //   1   2 | 4   5   6   7
    lit.remove();
    assertThat(list).containsExactly(1, 2, 4, 5, 6, 7);

    //   1   2 | 4   5   6   7
    //   1 | 2   4   5   6   7
    assertThat(lit.previous()).isEqualTo(2);
  }

  @Test
  public void iteratorTest2() {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

    ListIterator<Integer> lit = list.listIterator(2);

    //   1   2 | 3   4   5   6   7
    //   1   2   3 | 4   5   6   7
    assertThat(lit.next()).isEqualTo(3);

    //   1   2   3 | 4   5   6   7
    //   1   2 | 4   5   6   7
    lit.remove();
    assertThat(list).containsExactly(1, 2, 4, 5, 6, 7);

    //   1   2 | 4   5   6   7
    //   1 | 2   4   5   6   7
    assertThat(lit.previous()).isEqualTo(2);

    //   1 | 2   4   5   6   7
    //   1 | 4   5   6   7
    lit.remove();
    assertThat(list).containsExactly(1, 4, 5, 6, 7);

    //   1 | 4   5   6   7
    //   1   0 | 4   5   6   7
    lit.add(0);
    System.out.println("list = " + list);
  }

  @Test
  public void iteratorTest3() {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

    ListIterator<Integer> lit = list.listIterator(2);

    //   1   2 | 3   4   5   6   7
    //   1 | 2   3   4   5   6   7
    assertThat(lit.previous()).isEqualTo(2);

    //   1   2   3   4   5   6   7
    //   1 | 3   4   5   6   7
    lit.remove();
    assertThat(list).containsExactly(1, 3, 4, 5, 6, 7);

    //   1 | 3   4   5   6   7
    // | 1   3   4   5   6   7
    assertThat(lit.previous()).isEqualTo(1);

    // | 1   3   4   5   6   7
    // | 3   4   5   6   7
    lit.remove();
    assertThat(list).containsExactly(3, 4, 5, 6, 7);

    // | 3   4   5   6   7
    //   0 | 3   4   5   6   7
    lit.add(0);
    System.out.println("list = " + list);
  }
}