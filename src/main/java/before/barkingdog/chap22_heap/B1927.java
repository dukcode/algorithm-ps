package before.barkingdog.chap22_heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1927 {

    private static BufferedReader br;
    private static BufferedWriter bw;

    private static int n;

    private static class Heap {

        private int[] heap = new int[100005];
        int size = 0;

        public int pop() {
            int ret = heap[1];

            heap[1] = heap[size--];
            int idx = 1;

            while (idx * 2 <= size) {
                int lc = idx * 2;
                int rc = idx * 2 + 1;

                int minChild = lc;
                if (rc <= size && heap[rc] < heap[lc]) {
                    minChild = rc;
                }

                if (heap[idx] <= heap[minChild]) {
                    break;
                }

                swap(idx, minChild);

                idx = minChild;
            }

            return ret;
        }

        private void swap(int idx1, int idx2) {
            int tmp = heap[idx1];
            heap[idx1] = heap[idx2];
            heap[idx2] = tmp;
        }

        public void push(int x) {
            heap[++size] = x;

            int idx = size;
            while (idx > 1) {
                int pIdx = idx / 2;

                if (heap[pIdx] <= heap[idx]) {
                    break;
                }

                swap(pIdx, idx);
                idx = pIdx;
            }
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                bw.write(String.valueOf(heap.size() == 0 ? 0 : heap.pop()));
                bw.newLine();
                continue;
            }

            heap.push(num);
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
