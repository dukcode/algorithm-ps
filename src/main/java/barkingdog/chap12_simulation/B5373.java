package barkingdog.chap12_simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B5373 {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static class Cube {

        private BufferedWriter w;

        private static final char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};

        private static final int U = 0;
        private static final int D = 1;
        private static final int F = 2;
        private static final int B = 3;
        private static final int L = 4;
        private static final int R = 5;

        private static final int CW = 1;
        private static final int CCW = 3;

        private char[][][] planes = new char[6][][];

        public Cube(BufferedWriter bw) {
            this.w = bw;

            for (int i = 0; i < 6; ++i) {
                char[][] plane = new char[3][3];
                for (int y = 0; y < 3; ++y) {
                    for (int x = 0; x < 3; ++x) {
                        plane[y][x] = colors[i];
                    }
                }

                planes[i] = plane;
            }
        }

        private void rotate(char p, char d) {
            int planeNum = parsePlane(p);
            int dir = parseDir(d);

            rotatePlane(planeNum, dir);
            rotateSide(planeNum, dir);
        }

        private void printTop() throws IOException {
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    w.write(planes[U][y][x]);
                }
                w.newLine();
            }
        }

        private void rotateSide(int p, int dir) {

            char tmp;
            while (dir-- > 0) {
                switch (p) {
                    case U:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[F][0][i];
                            planes[F][0][i] = planes[R][0][i];
                            planes[R][0][i] = planes[B][0][i];
                            planes[B][0][i] = planes[L][0][i];
                            planes[L][0][i] = tmp;
                        }
                        break;
                    case D:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[R][2][i];
                            planes[R][2][i] = planes[F][2][i];
                            planes[F][2][i] = planes[L][2][i];
                            planes[L][2][i] = planes[B][2][i];
                            planes[B][2][i] = tmp;
                        }
                        break;
                    case F:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[R][i][0];
                            planes[R][i][0] = planes[U][2][i];
                            planes[U][2][i] = planes[L][2 - i][2];
                            planes[L][2 - i][2] = planes[D][0][2 - i];
                            planes[D][0][2 - i] = tmp;
                        }
                        break;
                    case B:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[R][i][2];
                            planes[R][i][2] = planes[D][2][2 - i];
                            planes[D][2][2 - i] = planes[L][2 - i][0];
                            planes[L][2 - i][0] = planes[U][0][i];
                            planes[U][0][i] = tmp;
                        }
                        break;
                    case L:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[U][i][0];
                            planes[U][i][0] = planes[B][2 - i][2];
                            planes[B][2 - i][2] = planes[D][i][0];
                            planes[D][i][0] = planes[F][i][0];
                            planes[F][i][0] = tmp;
                        }
                        break;
                    case R:
                        for (int i = 0; i < 3; ++i) {
                            tmp = planes[U][i][2];
                            planes[U][i][2] = planes[F][i][2];
                            planes[F][i][2] = planes[D][i][2];
                            planes[D][i][2] = planes[B][2 - i][0];
                            planes[B][2 - i][0] = tmp;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        private void rotatePlane(int p, int dir) {
            char[][] newPlane = new char[3][3];

            if (dir == CW) {
                for (int y = 0; y < 3; ++y) {
                    for (int x = 0; x < 3; ++x) {
                        newPlane[x][2 - y] = planes[p][y][x];
                    }
                }
            } else {
                for (int y = 0; y < 3; ++y) {
                    for (int x = 0; x < 3; ++x) {
                        newPlane[2 - x][y] = planes[p][y][x];
                    }
                }
            }

            planes[p] = newPlane;
        }

        private int parseDir(char d) {
            if (d == '+') {
                return CW;
            } else if (d == '-') {
                return CCW;
            }

            throw new IllegalStateException();
        }

        private int parsePlane(char p) {
            switch (p) {
                case 'U':
                    return U;
                case 'D':
                    return D;
                case 'F':
                    return F;
                case 'B':
                    return B;
                case 'L':
                    return L;
                case 'R':
                    return R;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private static int t;
    private static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("input.txt"));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            Cube cube = new Cube(bw);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                String token = st.nextToken();

                char plane = token.charAt(0);
                char dir = token.charAt(1);

                cube.rotate(plane, dir);
            }

            cube.printTop();
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
