package barkingdog.chap13_sorting1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class B11931 {
	private static BufferedReader br;
	private static BufferedWriter bw;

	private static int n;
	private static Integer[] arr;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		arr = new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr, (a, b) -> b - a);

		for (int i = 0; i < n; i++) {
			bw.write(String.valueOf(arr[i]));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

}
