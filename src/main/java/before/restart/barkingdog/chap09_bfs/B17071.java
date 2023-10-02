package before.restart.barkingdog.chap09_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17071 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int k;

	private static int LMT = 500_000;

	private static int[][] dist = new int[2][LMT + 1];

	private static class Point {
		int pos;
		int cntMove;

		public Point(int pos, int cntMove) {
			this.pos = pos;
			this.cntMove = cntMove;
		}
	}

	private static Queue<Point> q = new LinkedList<>();

	private static int ans;

	public static void main(String[] args) throws IOException, InterruptedException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dist[0][n] = 1;
		q.offer(new Point(n, 0));

		while (!q.isEmpty()) {
			Point curPoint = q.poll();
			int curPos = curPoint.pos;
			int curCntMove = curPoint.cntMove;
			int nxtCntMove = curCntMove + 1;
			for (int nxtPos : new int[] {curPos - 1, curPos + 1, curPos * 2}) {
				if (nxtPos < 0 || nxtPos > LMT) {
					continue;
				}

				if (dist[nxtCntMove % 2][nxtPos] != 0) {
					continue;
				}

				dist[nxtCntMove % 2][nxtPos] = dist[curCntMove % 2][curPos] + 1;
				q.offer(new Point(nxtPos, nxtCntMove));
			}

		}

		boolean found = false;
		while (k <= LMT) {
			int d = dist[ans % 2][k];
			if (d != 0 && d - 1 <= ans && (d - 1 - ans) % 2 == 0) {
				found = true;
				break;
			}
			k += ++ans;
		}

		bw.write(String.valueOf(found ? ans : -1));

		br.close();
		bw.close();
	}

}
