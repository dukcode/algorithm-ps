package barkingdog.chap02_basic;

import java.util.Scanner;

/**
 * B2753
 */
public class B2753 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();

        int ans = 0;
        if (year % 4 == 0) {
            if (year % 100 != 0) {
                ans = 1;
            }

            if (year % 400 == 0) {
                ans = 1;
            }
        }

        System.out.println(ans);
    }
}
