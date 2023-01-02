package barkingdog.chap02_basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B2752 {
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new FileReader("input.txt"));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Integer> ans = Arrays.stream(br.readLine().split(" "))
			.map(Integer::parseInt)
			.sorted()
			.collect(Collectors.toList());

		for (Integer num : ans) {
			bw.write(String.valueOf(num));
			bw.write(' ');
		}

		br.close();
		bw.close();
	}

}
