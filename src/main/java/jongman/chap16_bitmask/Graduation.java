package jongman.chap16_bitmask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href=https://www.algospot.com/judge/problem/read/GRADUATION>문제 URL</a>
 */
public class Graduation {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static final int INF = 987_654_321;

  private static int c;

  private static int n; // 전공 과목 수
  private static int k; // 들어야 할 과목 수
  private static int m; // 학기의 수
  private static int l; // 한 학기 최대로 들을 수 있는 과목 수

  private static int[] prerequisites;
  private static int[] classes;

  private static int[][] cache;


  public static void main(String[] args) throws IOException {
//    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      input();

      cache = new int[m][(1 << n)];
      for (int i = 0; i < m; ++i) {
        Arrays.fill(cache[i], -1);
      }

      int ans = calculateMinSemester(0, 0);
      bw.write(ans >= INF ? "IMPOSSIBLE" : String.valueOf(ans));
      bw.newLine();
    }

    bw.close();
    br.close();
  }

  /**
   * 현재 학기가 semester이고, 지금까지 들은 수업이 status일 때 현재 부터 졸업 가능한 최소 학기
   *
   * @param status   현재까지 들은 수업을 비트마스크로 표현
   * @param semester 현재 학기 (0학기 부터 시작)
   */
  private static int calculateMinSemester(int status, int semester) {
    // base case: k개 이상 이미 들은 경우
    if (Integer.bitCount(status) >= k) {
      return 0;
    }

    // base case: m학기가 전부 지난 경우
    if (semester == m) {
      return INF;
    }

    if (cache[semester][status] != -1) {
      return cache[semester][status];
    }
    cache[semester][status] = INF;

    // 이번 학기에 들을 수 있는 과목 찾기(들은 과목 지우기)
    int canTake = (classes[semester] & ~status);

    // 선수과목 안들은 과목 지우기
    for (int i = 0; i < n; ++i) {
      if (((canTake & (1 << i)) != 0) && ((prerequisites[i] & status) != prerequisites[i])) {
        canTake &= ~(1 << i);
      }
    }

    for (int take = canTake; take != 0; take = (take - 1) & canTake) {
      if (Integer.bitCount(take) > l) {
        continue;
      }

      cache[semester][status] = Math.min(cache[semester][status],
          1 + calculateMinSemester((status | take), semester + 1));
    }

    cache[semester][status] = Math.min(cache[semester][status],
        calculateMinSemester(status, semester + 1));

    return cache[semester][status];
  }

  private static void input() throws IOException {
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    l = Integer.parseInt(st.nextToken());

    prerequisites = new int[n];
    for (int subject = 0; subject < n; ++subject) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      for (int i = 0; i < r; ++i) {
        int prerequisite = Integer.parseInt(st.nextToken());
        prerequisites[subject] |= (1 << prerequisite);
      }
    }

    classes = new int[m];
    for (int semester = 0; semester < m; ++semester) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      for (int i = 0; i < t; ++i) {
        int clazz = Integer.parseInt(st.nextToken());
        classes[semester] |= (1 << clazz);
      }
    }
  }

}
