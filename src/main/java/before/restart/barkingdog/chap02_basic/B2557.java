package before.restart.barkingdog.chap02_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2557 {
	private static BufferedWriter bw;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		bw.write("Hello World!");

		bw.close();
	}

}
