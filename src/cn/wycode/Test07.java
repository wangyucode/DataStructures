package cn.wycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

/**
 * ## 作业1：Reversing Linked List（25 分）
 * <p>
 * Given a constant K and a singly linked list L, you are supposed to reverse the links of every K elements on L. For example, given L being 1→2→3→4→5→6, if K=3, then you must output 3→2→1→6→5→4; if K=4, you must output 4→3→2→1→5→6.
 * <p>
 * Input Specification:
 * <p>
 * Each input file contains one test case. For each case, the first line contains the address of the first node, a positive N (≤10
 * ​5
 * ​​ ) which is the total number of nodes, and a positive K (≤N) which is the length of the sublist to be reversed. The address of a node is a 5-digit nonnegative integer, and NULL is represented by -1.
 * <p>
 * Then N lines follow, each describes a node in the format:
 * <p>
 * `Address Data Next`
 * where `Address` is the position of the node, `Data` is an integer, and `Next` is the position of the nextAddress node.
 * <p>
 * Output Specification:
 * <p>
 * For each case, output the resulting ordered linked list. Each node occupies a line, and is printed in the same format as in the input.
 * <p>
 * Sample Input:
 * ```bash
 * 00100 6 4
 * 00000 4 99999
 * 00100 1 12309
 * 68237 6 -1
 * 33218 3 00000
 * 99999 5 68237
 * 12309 2 33218
 * ```
 * Sample Output:
 * ```bash
 * 00000 4 33218
 * 33218 3 12309
 * 12309 2 00100
 * 00100 1 99999
 * 99999 5 68237
 * 68237 6 -1
 * ```
 */
public class Test07 {
    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //读第一行
        String[] line1Array = getStringArray(reader);
        String firstAddress = line1Array[0];
        int count = Integer.parseInt(line1Array[1]);
        int k = Integer.parseInt(line1Array[2]);

        //读取所有节点
        ArrayList<Node> nodes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String[] lineArray = getStringArray(reader);
            Node node = new Node();
            node.address = lineArray[0];
            node.data = Integer.parseInt(lineArray[1]);
            node.nextAddress = lineArray[2];
            nodes.add(node);
        }


        //按链表顺序加入队列
        LinkedList<Node> queue = new LinkedList<>();
        String address = firstAddress;
        while (!address.equals("-1")) {
            for (int i = 0; i < nodes.size(); i++) { //循环匹配地址
                Node n = nodes.get(i);
                if (Objects.equals(address, n.address)) { //找到则加入队列
                    queue.add(n);
                    address = n.nextAddress; //查找地址赋值为当前节点的下一个地址
                    nodes.remove(n); //处理完的移出节点数组，减少循环量
                    break;
                }
            }
        }

        Stack<Node> stack = new Stack<>();
        boolean isCanConvert = queue.size() >= k; //是否够反转
        while (!queue.isEmpty()) { //循环出队列
            Node n = queue.remove();
            if (isCanConvert) { //够反转则压入堆栈
                stack.push(n);
                if (stack.size() == k) { //压够反转数量就全部输出
                    isCanConvert = queue.size() >= k; //再次检查是否够反转
                    while (!stack.isEmpty()) {
                        //重新赋值next
                        Node nStack = stack.pop();
                        if (stack.isEmpty()) { //如果栈里没了，地址就等于队列的下一个
                            if (queue.isEmpty()) { //队列也没了，地址等于-1
                                nStack.nextAddress = "-1";
                            } else {
                                //如果剩下的还能反转
                                if (isCanConvert) {
                                    nStack.nextAddress = queue.get(k - 1).address;
                                } else {
                                    nStack.nextAddress = queue.peek().address;
                                }
                            }
                        } else {
                            nStack.nextAddress = stack.peek().address;
                        }
                        print(nStack);
                    }
                }
            } else { //不够反转 直接输出节点
                print(n);
            }
        }

    }


    private static void print(Node n) {
        System.out.println(n.address + " " + n.data + " " + n.nextAddress);
    }

    private static String[] getStringArray(BufferedReader reader) {
        String line1 = null;
        try {
            line1 = reader.readLine();
        } catch (Exception ignore) {
        }
        return line1.split(" ");
    }

    static class Node {
        String address;
        int data;
        String nextAddress;
    }
}
