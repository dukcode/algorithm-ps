package before.barkingdog.chap06_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B18258 {

    public static final int MX = 2000005;
    public static int[] q = new int[MX];
    public static int f = 0;
    public static int b = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    q[b++] = num;
                    break;
                case "pop":
                    int popValue = f == b ? -1 : q[f++];
                    bw.write(String.valueOf(popValue));
                    bw.newLine();
                    break;
                case "size":
                    bw.write(String.valueOf(b - f));
                    bw.newLine();
                    break;
                case "empty":
                    int emptyValue = f == b ? 1 : 0;
                    bw.write(String.valueOf(emptyValue));
                    bw.newLine();
                    break;
                case "front":
                    int frontValue = f == b ? -1 : q[f];
                    bw.write(String.valueOf(frontValue));
                    bw.newLine();
                    break;
                case "back":
                    int backValue = f == b ? -1 : q[b - 1];
                    bw.write(String.valueOf(backValue));
                    bw.newLine();
                    break;
                default:
                    break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

}
