package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5014_1 {

    static int[] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int f = Integer.parseInt(st.nextToken());   // 건물의 층 수
        int s = Integer.parseInt(st.nextToken());   // 출발층
        int g = Integer.parseInt(st.nextToken());   // 목적지 층
        int u = Integer.parseInt(st.nextToken());   // 위로 u층을 이동하는 버튼
        int d = Integer.parseInt(st.nextToken()) * -1;   // 아래로 d층을 이동하는 버튼

        building = new int[f + 1];
        building[s] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.offer(s);

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == g) {
                break;
            }

            for (Integer move : new int[]{u, d}) {
                int stair = cur + move;

                if (stair <= 0 || stair > f) {
                    continue;
                }

                if (building[stair] != 0) {
                    continue;
                }

                q.offer(stair);
                building[stair] = building[cur] + 1;
            }
        }

        int ans = building[g] - 1;

        if (ans == -1) {
            bw.write("use the stairs");
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
        br.close();

    }

}
