package kakao.intern_2022;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class K2 {

    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 =
                Arrays.stream(queue1).boxed().collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> q2 =
                Arrays.stream(queue2).boxed().collect(Collectors.toCollection(LinkedList::new));

        int limit = q1.size() + q2.size();

        long sum1 = q1.stream().mapToLong(i -> i).sum();
        long sum2 = q2.stream().mapToLong(i -> i).sum();

        if (sum1 + sum2 % 2 == 1) {
            return -1;
        }

        int ans = 0;
        boolean impossible = false;
        while (sum1 != sum2) {
            if (ans >= limit * 2) {
                impossible = true;
                break;
            }
            ans++;
            if (sum1 > sum2) {
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            } else {
                int num = q2.poll();
                q1.offer(num);
                sum2 -= num;
                sum1 += num;
            }
        }

        return impossible ? -1 : ans;
    }
}
