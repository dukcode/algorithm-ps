package barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B9466 {

    static int[] arr;
    static int[] state;

    static final int NOT_VISITED = 0;
    static final int CYCLE_IN = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            state = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; ++i) {

                int data = Integer.parseInt(st.nextToken());
                if (data == i) {
                    state[i] = CYCLE_IN;
                }
                arr[i] = data;
            }

            Loop:
            for (int i = 1; i <= n; ++i) {

                if (state[i] != NOT_VISITED) {
                    continue;
                }

                int cur = i;
                while (true) {
                    state[cur] = i;
                    cur = arr[cur];

                    if (state[cur] == i) {
                        while (state[cur] != CYCLE_IN) {
                            state[cur] = CYCLE_IN;
                            cur = arr[cur];
                        }
                        continue Loop;
                    } else if (state[cur] != NOT_VISITED) {
                        continue Loop;
                    }
                }
            }

            long ans = Arrays.stream(state).filter(i -> i != -1).count() - 1;
            bw.write(String.valueOf(ans));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
