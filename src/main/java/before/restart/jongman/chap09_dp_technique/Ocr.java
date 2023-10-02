package before.restart.jongman.chap09_dp_technique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Ocr {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int m;    // 원문에 출현할 수 있는 단어의 수
	private static int q;    // 처리해야할 문장의 수

	private static String[] words;
	private static Map<String, Integer> wordIdxMap;    // 출현 단어와 순서
	private static double[] b;    // 단어가 처음에 출현할 확률
	private static double[][] t; // t[i][j] -> i 다음 단어가 j일 확률
	private static double[][] k;    // k[i][j] -> i를 j로 분류할 확률
	private static int n;
	private static int[] sentence;    // 문장

	private static double[][] cache;
	private static int[][] choice;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("input.txt"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		wordIdxMap = new HashMap<>();
		words = new String[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			String word = st.nextToken();
			words[i] = word;
			wordIdxMap.put(word, i);
		}

		b = new double[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			b[i] = Math.log10(Double.parseDouble(st.nextToken()));
		}

		t = new double[m][m];
		for (int y = 0; y < m; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < m; x++) {
				t[y][x] = Math.log10(Double.parseDouble(st.nextToken()));
			}
		}

		k = new double[m][m];
		for (int y = 0; y < m; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < m; x++) {
				k[y][x] = Math.log10(Double.parseDouble(st.nextToken()));
			}
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			cache = new double[n][m];
			choice = new int[n][m];
			for (int j = 0; j < n; j++) {
				Arrays.fill(cache[j], 1.0);
				Arrays.fill(choice[j], -1);
			}

			sentence = new int[n];
			for (int j = 0; j < n; j++) {
				sentence[j] = wordIdxMap.get(st.nextToken());
			}

			recognize(0, 0);
			bw.write(reconstruct(0, 0));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static double recognize(int sentenceIdx, int previousWordIdx) {
		if (sentenceIdx == n) {
			return 0.0;
		}

		if (cache[sentenceIdx][previousWordIdx] != 1.0) {
			return cache[sentenceIdx][previousWordIdx];
		}

		cache[sentenceIdx][previousWordIdx] = Math.log10(0.0);
		for (int currentWordIdx = 0; currentWordIdx < m; currentWordIdx++) {
			double candidate = k[currentWordIdx][sentence[sentenceIdx]] +
				(sentenceIdx == 0 ? b[currentWordIdx] : t[previousWordIdx][currentWordIdx]) +
				recognize(sentenceIdx + 1, currentWordIdx);

			if (candidate > cache[sentenceIdx][previousWordIdx]) {
				cache[sentenceIdx][previousWordIdx] = candidate;
				choice[sentenceIdx][previousWordIdx] = currentWordIdx;
			}
		}

		return cache[sentenceIdx][previousWordIdx];
	}

	private static String reconstruct(int sentenceIdx, int previousWordIdx) {
		int choose = choice[sentenceIdx][previousWordIdx];
		String ret = words[choose];
		if (sentenceIdx < n - 1) {
			ret = ret + " " + reconstruct(sentenceIdx + 1, choose);
		}

		return ret;
	}

}
