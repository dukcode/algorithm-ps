package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B2587 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static List<Integer> arr = new ArrayList<>();
	private static int sum;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 5; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			arr.add(num);
		}

		bw.write(String.valueOf(sum / 5));
		bw.newLine();

		Collections.sort(arr);
		bw.write(String.valueOf(arr.get(2)));

		br.close();
		bw.close();
	}

}
