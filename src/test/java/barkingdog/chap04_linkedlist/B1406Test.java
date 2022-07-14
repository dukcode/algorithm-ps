package barkingdog.chap04_linkedlist;

import java.util.LinkedList;
import java.util.ListIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class B1406Test {

    LinkedList<Integer> ll = new LinkedList<>();

    @BeforeEach
    void setUp() {
        ll.clear();
        for (int i = 0; i < 10; ++i) {
            ll.add(i);
        }
    }

    @Test
    @DisplayName("iterator가 가르키는 곳")
    public void iterator() throws Exception {
        ListIterator<Integer> it = ll.listIterator(0);
        System.out.println(it.nextIndex());
        System.out.println(it.next());
        System.out.println(it.next());
    }

    @Test
    @DisplayName("iterator가 가르키는 곳")
    public void iterator2() throws Exception {
        ListIterator<Integer> it = ll.listIterator(ll.size());
        System.out.println(it.previous());
    }

    public void print() {
        for (Integer integer : ll) {
            System.out.println(integer);
        }
    }
}
