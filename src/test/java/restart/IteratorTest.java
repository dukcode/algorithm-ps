package restart;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

public class IteratorTest {

	@Test
	void listIterator() {

		List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
		Iterator<Integer> it = list.iterator();

		// 순회법 <- 요소 사이사이를 가리키고 있음
		while (it.hasNext()) {
			System.out.printf("%2d ", it.next());
		}
		System.out.println();
		System.out.println("-------------");

		ListIterator<Integer> listIt = list.listIterator();
		System.out.println(listIt.next());
		listIt.remove();    // next()나 previous()로 불러왔던 마지막 요소를 제거
		System.out.println(list);
	}

	@Test
	void stringCharacterIterator() {
		String str = "12345";
		StringCharacterIterator sci = new StringCharacterIterator(str);

		// 순회법 -> 요소 자체를 가리키고 있음
		while (sci.current() == StringCharacterIterator.DONE) {
			System.out.printf("%c", sci.current());
			sci.next();
		}
	}
}
