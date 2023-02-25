package restart.barkingdog.chap02_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class B10171 {
	private static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write("\\    /\\\n");
		bw.write(" )  ( ')\n");
		bw.write("(  /  )\n");
		bw.write(" \\(__)|\n");
		bw.close();
	}

}
