package restart.jongman.chap07_divide_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Karatsuba {

	private static BufferedReader br;
	private static BufferedWriter bw;

	private static List<Integer> a = new ArrayList<>();
	private static List<Integer> b = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String line = br.readLine();
		for (int i = line.length() - 1; i >= 0; --i) {
			a.add(line.charAt(i) - '0');
		}

		line = br.readLine();
		for (int i = line.length() - 1; i >= 0; --i) {
			b.add(line.charAt(i) - '0');
		}

		List<Integer> ans = multiply(a, b);
		for (int i = ans.size() - 1; i >= 0; --i) {
			bw.write(ans.get(i) + '0');
		}

		br.close();
		bw.close();
	}

	private static List<Integer> multiply(List<Integer> a, List<Integer> b) {
		List<Integer> ret = new ArrayList<>(Collections.nCopies(a.size() + b.size() - 1, 0));

		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				ret.set(i + j, ret.get(i + j) + a.get(i) * b.get(j));
			}
		}

		normalize(ret);
		return ret;
	}

	private static void normalize(List<Integer> num) {
		num.add(0);

		for (int i = 0; i < num.size() - 1; i++) {
			if (num.get(i) >= 0) {
				num.set(i + 1, num.get(i + 1) + num.get(i) / 10);
				num.set(i, num.get(i) % 10);
			} else {
				int borrow = (Math.abs(num.get(i)) + 9) / 10;
				num.set(i + 1, num.get(i + 1) - borrow);
				num.set(i, num.get(i) + borrow * 10);
			}
		}

		while (num.size() > 1 && num.get(num.size() - 1) == 0) {
			num.remove(num.size() - 1);
		}
	}

}
