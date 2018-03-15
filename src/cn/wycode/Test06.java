package cn.wycode;

import java.util.Scanner;

/**
 * ## 作业1：一元多项式的乘法与加法运算（20 分）
 * <p>
 * 设计函数分别求两个一元多项式的乘积与和。
 * <p>
 * 输入格式:
 * <p>
 * 输入分2行，每行分别先给出多项式非零项的个数，再以指数递降方式输入一个多项式非零项系数和指数（绝对值均为不超过1000的整数）。数字间以空格分隔。
 * <p>
 * 输出格式:
 * <p>
 * 输出分2行，分别以指数递降方式输出乘积多项式以及和多项式非零项的系数和指数。数字间以空格分隔，但结尾不能有多余空格。零多项式应输出0 0。
 * <p>
 * 输入样例:
 * ```bash
 * 4 3 4 -5 2  6 1  -2 0
 * 3 5 20  -7 4  3 1
 * ```
 * 输出样例:
 * ```bash
 * 15 24 -25 22 30 21 -10 20 -21 8 35 6 -33 5 14 4 -15 3 18 2 -6 1
 * 5 20 -4 4 -5 2 9 1 -2 0
 * ```
 */
public class Test06 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolyNode first1 = getInput(scanner);
        PolyNode first2 = getInput(scanner);
        PolyNode result1 = add(first1, first2);
        PolyNode result2 = multiply(first1, first2);
        print(result2);
        print(result1);

    }

    /**
     * 逐项插入算法
     *
     * @param first1
     * @param first2
     * @return
     */
    private static PolyNode multiply(PolyNode first1, PolyNode first2) {
        PolyNode p1 = first1;
        PolyNode p2 = first2;

        PolyNode first = new PolyNode(); //结果，先构造一个空的头节点
        PolyNode rear = first; //将尾部指向这个头
        while (p2 != null) { //先用第一项乘以p2构造一个初始多项式
            PolyNode next = new PolyNode(); //构造一个新节点
            next.coef = p1.coef * p2.coef; //系数相乘
            next.expon = p1.expon + p2.expon; //指数相加
            rear.next = next; //尾部指针指向新的节点
            rear = next; //新尾部等于新添加的节点
            p2 = p2.next; //移动p2指针
        }
        p1 = p1.next; //把p1指针向后挪1位
        while (p1 != null) {
            p2 = first2; //将p2指针归位
            rear = first; //尾部指针归位
            while (p2 != null) {
                int e = p1.expon + p2.expon; //指数相加
                int c = p1.coef * p2.coef; //系数相乘
                while (rear.next != null && rear.next.expon > e) { //寻找比当前指数小或者等的位置
                    rear = rear.next; //rear 往后挪
                }
                if (rear.next != null && rear.next.expon == e) { //挪了之后如果下一项存在，且指数相等，则合并系数
                    if (rear.next.coef + c == 0) { //和下一项系数相加等于0，则删掉这一项
                        rear.next = rear.next.next;
                    } else { //系数相加不为0，就要合并系数
                        rear.next.coef += c;
                    }
                } else { //指数不等，需要创建一个新节点插入
                    PolyNode newPoly = new PolyNode(); //构造一个新节点
                    newPoly.coef = c;
                    newPoly.expon = e;
                    newPoly.next = rear.next;//新插入的节点下一个=当前尾部的下一个
                    rear.next = newPoly; //当前尾部的下一个等于新节点
                }

                p2 = p2.next;//p2处理完毕，将p2挪到下一个
            }
            p1 = p1.next;//p2遍历完毕，将p1往后挪
        }
        first = first.next; //将头部挪到真实的头部位置，Java会自动回收没有引用的头节点

        return first;
    }

    private static void print(PolyNode result) {
        PolyNode p = result;
        StringBuilder sb = new StringBuilder();
        if (p == null) { //零多项式输出0 0
            sb.append("0 0");
        }
        boolean isFirst = true;
        while (p != null) {
            if (!isFirst)
                sb.append(' ');
            sb.append(p.coef);
            sb.append(' ');
            sb.append(p.expon);
            p = p.next;
            isFirst = false;
        }
        System.out.println(sb.toString());
    }

    private static PolyNode add(PolyNode first1, PolyNode first2) {
        PolyNode p1 = first1;
        PolyNode p2 = first2;

        PolyNode first = new PolyNode(); //结果，先构造一个空的头节点
        PolyNode rear = first; //将尾部指向这个头
        while (p1 != null && p2 != null) { //当两个指针都不为空时
            if (p1.coef == 0) { //系数为0,直接省略
                p1 = p1.next;
            } else if (p2.coef == 0) {
                p2 = p2.next;
            } else if (p1.expon == p2.expon) { //指数相等，则系数相加后串到后面
                int coef = p1.coef + p2.coef;  //系数相加
                if (coef != 0) {
                    PolyNode next = new PolyNode(); //构造一个新节点
                    next.coef = coef;
                    next.expon = p1.expon;
                    rear.next = next; //尾部指针指向新的节点
                    rear = next; //新尾部等于新添加的节点
                }
                p1 = p1.next; //移动指针
                p2 = p2.next;
            } else if (p1.expon > p2.expon) {//指数大的串到后面
                PolyNode next = new PolyNode(); //构造一个新节点
                next.coef = p1.coef;
                next.expon = p1.expon;
                rear.next = next; //尾部指针指向新的节点
                rear = next; //新尾部等于新添加的节点
                p1 = p1.next; //移动指针
            } else {
                PolyNode next = new PolyNode(); //构造一个新节点
                next.coef = p2.coef;
                next.expon = p2.expon;
                rear.next = next; //尾部指针指向新的节点
                rear = next; //新尾部等于新添加的节点
                p2 = p2.next; //移动指针
            }
        }

        while (p1 != null) { //p1没完，把所有p1追加到后面
            if (p1.coef != 0) { //系数不为0才，添加，否则继续找因为后面可以能还有系数不为0的
                PolyNode next = new PolyNode(); //构造一个新节点
                next.coef = p1.coef;
                next.expon = p1.expon;
                rear.next = next; //尾部指针指向新的节点
                rear = next; //新尾部等于新添加的节点
            }
            p1 = p1.next; //移动指针
        }

        while (p2 != null && p2.coef != 0) { //p2没完，把所有p2追加到后面
            if (p2.coef != 0) { //系数不为0才，添加，否则继续找因为后面可以能还有系数不为0的
                PolyNode next = new PolyNode(); //构造一个新节点
                next.coef = p2.coef;
                next.expon = p2.expon;
                rear.next = next; //尾部指针指向新的节点
                rear = next; //新尾部等于新添加的节点
            }
            p2 = p2.next; //移动指针
        }
        first = first.next; //将头部挪到真实的头部位置，Java会自动回收没有引用的头节点
        return first;
    }

    private static PolyNode getInput(Scanner scanner) {
        int count = scanner.nextInt(); //得到总项数
        PolyNode first = new PolyNode(); //构造一个空的头
        PolyNode rear = first; //构造一个尾部的指针
        while (count > 0) {
            PolyNode next = new PolyNode(); //读取一个节点
            next.coef = scanner.nextInt();
            next.expon = scanner.nextInt();
            rear.next = next; //将尾部的指针指向读到的这个节点
            rear = next; //将尾部指针挪到新添加的节点
            count--; //循环
        }
        first = first.next; //将头部挪到真实的头部位置，Java会自动回收没有引用的头节点
        return first;
    }


    static class PolyNode {
        int coef;
        int expon;
        PolyNode next;
    }
}
