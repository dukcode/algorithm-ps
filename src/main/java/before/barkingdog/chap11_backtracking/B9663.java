package before.barkingdog.chap11_backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B9663 {

    static int n;
    static boolean[] isUsed1;   // 세로
    static boolean[] isUsed2;    // 오른쪽 아래 대각선 (차가 같음)
    static boolean[] isUsed3;   // 오른쪽 위 대각선 (합이 같음)
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        isUsed1 = new boolean[n];
        isUsed2 = new boolean[2 * n - 1];
        isUsed3 = new boolean[2 * n - 1];

        func(0);
        bw.write(String.valueOf(count));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int y) {
        if (y == n) {
            count++;
            return;
        }

        for (int x = 0; x < n; ++x) {
            if (isUsed1[x] || isUsed2[y - x + (n - 1)] || isUsed3[y + x]) {
                continue;
            }

            isUsed1[x] = true;
            isUsed2[y - x + (n - 1)] = true;
            isUsed3[y + x] = true;
            func(y + 1);
            isUsed1[x] = false;
            isUsed2[y - x + (n - 1)] = false;
            isUsed3[y + x] = false;
        }


    }
}

