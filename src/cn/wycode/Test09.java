package cn.wycode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Given a tree, you are supposed to list all the leaves in the order of top down, and left to right.
 * <p>
 * - Input Specification:
 * <p>
 * Each input file contains one test case. For each case, the first line gives a positive integer N (≤10) which is the total number of nodes in the tree -- and hence the nodes are numbered from 0 to N−1. Then N lines follow, each corresponds to a node, and gives the indices of the left and right children of the node. If the child does not exist, a "-" will be put at the position. Any pair of children are separated by a space.
 * <p>
 * - Output Specification:
 * <p>
 * For each test case, print in one line all the leaves' indices in the order of top down, and left to right. There must be exactly one space between any adjacent numbers, and no extra space at the end of the line.
 * <p>
 * - Sample Input:
 * <p>
 * ```bash
 * 8
 * 1 -
 * - -
 * 0 -
 * 2 7
 * - -
 * - -
 * 5 -
 * 4 6
 * ```
 * Sample Output:
 * <p>
 * <p>
 * ```bash
 * 4 1 5
 * ```
 */
public class Test09 {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sum;
        sum = Integer.parseInt(reader.readLine());

        String[] inputs = new String[sum];

        for (int i = 0; i < sum; i++) {
            inputs[i] = reader.readLine();
        }

        int root_num = findRoot(inputs);
        StringBuilder sb = new StringBuilder();
        Queue<Node> nodes = new LinkedList<>();

        Node root =  getNode(root_num,inputs[root_num]);
        nodes.add(root);
        checkTree(nodes, sb, inputs);
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    private static Node getNode(int root_num, String input) {
        Node node = new Node();
        node.data = root_num;
        String[] lineArg = input.split(" ");
        if (!Objects.equals("-", lineArg[0])) {
            node.left = Integer.parseInt(lineArg[0]);
        }
        if (!Objects.equals("-", lineArg[1])) {
            node.right = Integer.parseInt(lineArg[1]);
        }
        return node;
    }

    private static void checkTree(Queue<Node> nodes, StringBuilder sb, String[] inputs) {
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.left<0 && node.right<0) {
                sb.append(node.data);
                sb.append(' ');
            }
            if (node.left>=0) {
                Node n_l = getNode(node.left,inputs[node.left]);
                nodes.add(n_l);
            }
            if (node.right>=0) {
                Node n_r = getNode(node.right,inputs[node.right]);
                nodes.add(n_r);
            }
        }
    }


    private static int findRoot(String[] inputs) {
        int[] link = new int[inputs.length];
        for (String input : inputs) {
            String[] lineArg = input.split(" ");
            if (!Objects.equals("-", lineArg[0])) {
                int left_num = Integer.parseInt(lineArg[0]);
                link[left_num] = 1;
            }
            if (!Objects.equals("-", lineArg[1])) {
                int right_num = Integer.parseInt(lineArg[1]);
                link[right_num] = 1;
            }
        }
        int i;
        for (i = 0; i < link.length; i++) {
            if (link[i] == 0) {
                break;
            }
        }

        return i;
    }

    static class Node{
        int data =-1;
        int left = -1;
        int right = -1;
    }
}
