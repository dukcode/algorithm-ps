package kakao.intern_2022;

import java.util.HashMap;
import java.util.Map;

public class K1 {

    private final char[] category = {'r', 't', 'c', 'f', 'j', 'm', 'a', 'n'};

    public String solution(String[] survey, int[] choices) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 8; ++i) {
            map.put(category[i], 0);
        }

        int n = survey.length;
        for (int i = 0; i < n; ++i) {
            String q = survey[i];
            char first = q.charAt(0);
            char second = q.charAt(1);

            int choice = choices[i];

            if (choice < 4) {
                map.put(first, map.get(first) + (4 - choice));
            } else if (choice > 4) {
                map.put(second, map.get(second) + (choice - 4));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            char first = category[i * 2];
            char second = category[i * 2 + 1];

            int firstScore = map.get(first);
            int secondScore = map.get(second);

            if (firstScore >= secondScore) {
                sb.append(Character.toUpperCase(first));
            } else {
                sb.append(Character.toUpperCase(second));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        K1 solution = new K1();
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};

        String ans = solution.solution(survey, choices);
        System.out.println("ans = " + ans);
    }
}
