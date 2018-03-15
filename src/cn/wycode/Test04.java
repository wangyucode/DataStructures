package cn.wycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 01-复杂度2 Maximum Subsequence Sum（25 分）
 * Given a sequence of K integers { N1, N2, ..., NK}. A continuous subsequence is defined to be { Ni, Ni+1, ..., Nj } where 1≤i≤j≤K. The Maximum Subsequence is the continuous subsequence which has the largest sum of its elements. For example, given sequence { -2, 11, -4, 13, -5, -2 }, its maximum subsequence is { 11, -4, 13 } with the largest sum being 20.
 * <p>
 * Now you are supposed to find the largest sum, together with the first and the last numbers of the maximum subsequence.
 * <p>
 * Input Specification:
 * <p>
 * Each input file contains one test case. Each case occupies two lines. The first line contains a positive integer K (≤10000). The second line contains K numbers, separated by a space.
 * <p>
 * Output Specification:
 * <p>
 * For each test case, output in one line the largest sum, together with the first and the last numbers of the maximum subsequence. The numbers must be separated by one space, but there must be no extra space at the end of a line. In case that the maximum subsequence is not unique, output the one with the smallest indices i and j (as shown by the sample case). If all the K numbers are negative, then its maximum sum is defined to be 0, and you are supposed to output the first and the last numbers of the whole sequence.
 * <p>
 * Sample Input:
 * ```
 * 10
 * -10 1 2 3 4 -5 -23 3 7 -21
 * ```
 * Sample Output:
 * ```
 * 10 1 4
 * ```
 */
public class Test04 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String countString = null;
        String numbersString =null;
        try {
            countString = reader.readLine();
            numbersString = reader.readLine();
        }catch (Exception ignored){

        }
        int[] n = new int[Integer.parseInt(countString)];
        String[] numbersStringArray = numbersString.split(" ");

        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(numbersStringArray[i]);
        }


//        Scanner scanner = new Scanner(System.in);
//        int k = scanner.nextInt();
//        int[] n = new int[k];
//
//        for (int i = 0; i < k; i++) {
//            n[i] = scanner.nextInt();
//        }

        maximumSubsequenceSum(n);

    }

    /**
     * 在线处理算法，任意时刻给出的都是当前最大子数列和
     *
     * @param n
     * @return
     */
    private static void maximumSubsequenceSum(int[] n) {
        int thisMax = 0;
        int maxSum = 0;

        int startI = 0;
        int endI = n.length - 1;
        int temp = 0;
        boolean isMaxStart = true;
        for (int i = 0; i < n.length; i++) {
            thisMax += n[i];

            if (thisMax < 0) {
                thisMax = 0;
                temp = 0;
                isMaxStart = true;
            } else {

                if (isMaxStart) {
                    temp = i;
                    isMaxStart = false;
                }

                if (thisMax > maxSum) {

                    maxSum = thisMax;
                    endI = i;
                    startI = temp;
                }

                if (maxSum == 0) {
                    startI = temp;
                    endI = i;
                }
            }
        }


        System.out.println(maxSum + " " + n[startI] + " " + n[endI]);
    }
}
