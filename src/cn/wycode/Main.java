package cn.wycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
public class Main {
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

        //设置节点顺序
        if (count < k) {
            for (Node n : nodes) {
                print(n);
            }
            return;
        }
        String address = firstAddress;
        Stack<Node> stack = new Stack<>();
        boolean isOver = false;
        while (!address.equals("-1")) {
            for (int i = 0; i < nodes.size(); i++) { //循环匹配地址
                Node n = nodes.get(i);
                if (isOver) {
                    print(n);
                    address = n.nextAddress; //查找地址赋值为当前节点的下一个地址
                    continue;
                }
                if (Objects.equals(address, n.address)) {
                    stack.push(n);
                    if (stack.size() == k) { //如果栈内节点数量==反转数量，全部挨个出栈输出
                        while (!stack.isEmpty()) {
                            print(stack.pop());
                        }
                        if(nodes.size()-i-1<k){ //输出完成后检查还够不够反转（size要除去已经压入的节点）
                            isOver = true;
                        }
                    }
                    address = n.nextAddress; //查找地址赋值为当前节点的下一个地址
                    nodes.remove(n); //处理完的移出节点数组，减少循环量
                    break;
                }
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
