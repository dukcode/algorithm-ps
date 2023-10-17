import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private static final int INF = 987_654_321;

    private int[] cache;
    private int n;
    private int targetIdx;
    private boolean[][] adj;

    @org.junit.jupiter.api.Test
    void test() {
        String begin = "hit";
        String target = "cog";
        String[] words = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};

        solution(begin, target, words);
    }


    public int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.stream(words).collect(Collectors.toList());
        wordList.add(begin);

        targetIdx = wordList.indexOf(target);
        if (targetIdx == -1) {
            return 0;
        }
        System.out.println("targetIdx = " + targetIdx);

        n = wordList.size();

        adj = new boolean[n][n];
        for (int y = 0; y < n; ++y) {
            for (int x = y + 1; x < n; ++x) {
                if (canChange(wordList.get(y), wordList.get(x))) {
                    adj[y][x] = adj[x][y] = true;
                }
            }
        }


        cache = new int[n];
        Arrays.fill(cache, INF);

        int ret = dfs(n - 1);
        return ret >= INF ? 0 : ret;
    }

    private int dfs(int now) {
        if (now == targetIdx) {
            return 0;
        }

        if (cache[now] != INF) {
            return cache[now];
        }

        for (int next = 0; next < n; ++next) {
            if (adj[now][next]) {
                cache[now] = Math.min(cache[now], 1 + dfs(next));
            }
        }

        return cache[now];
    }

    private boolean canChange(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int n = a.length();
        int diffCnt = 0;
        for (int i = 0; i < n; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCnt++;
            }
        }

        return diffCnt == 1;
    }
}
