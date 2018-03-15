package cn.wycode;

import java.util.Scanner;

/*

  01-复杂度1 最大子列和问题（20 分）
 给定K个整数组成的序列{ N​1, N2, ..., NK}，“连续子列”被定义为{ N​i, Ni+1, ..., N​j}，其中 1≤i≤j≤K。
 “最大子列和”则被定义为所有连续子列元素的和中最大者。
 例如给定序列{ -2, 11, -4, 13, -5, -2 }，其连续子列{ 11, -4, 13 }有最大的和20。
 现要求你编写程序，计算给定整数序列的最大子列和。

 本题旨在测试各种不同的算法在各种数据情况下的表现。

 各组测试数据特点如下：

 数据1：与样例等价，测试基本正确性；
 数据2：102个随机整数；
 数据3：103个随机整数；
 数据4：104个随机整数；
 数据5：105个随机整数；
 输入格式:

 输入第1行给出正整数K (≤100000)；第2行给出K个整数，其间以空格分隔。

 输出格式:

 在一行中输出最大子列和。如果序列中所有整数皆为负数，则输出0。

 输入样例:

 6
 -2 11 -4 13 -5 -2
 输出样例:

 20
 */
public class Test03 {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] n = new int[k];
        for (int i = 0; i < k; i++) {
            if (scanner.hasNext()) {
                n[i] = scanner.nextInt();
            }
        }
        System.out.println(maximumSubsequenceSum(n));
    }

    /**
     * 在线处理算法，任意时刻给出的都是当前最大子数列和
     * @param n
     * @return
     */
    private static int maximumSubsequenceSum(int[] n) {
        int thisMax = 0;
        int maxSum = 0;
        for (int i = 0; i < n.length; i++) {
            thisMax += n[i];

            if (thisMax < 0) {
                thisMax = 0;
            } else if (thisMax > maxSum) {
                maxSum = thisMax;
            }
        }
        return maxSum;
    }
}
