package cn.wycode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * An inorder binary tree traversal can be implemented in a non-recursive way with a stack. For example, suppose that when a 6-node binary tree (with the keys numbered from 1 to 6) is traversed, the stack operations are: push(1); push(2); push(3); pop(); pop(); push(4); pop(); pop(); push(5); push(6); pop(); pop(). Then a unique binary tree (shown in Figure 1) can be generated from this sequence of operations. Your task is to give the postorder traversal sequence of this tree.
 * <p>
 * - Figure 1
 * <p>
 * ![Figure 1](/blog/images/20180327_data_structures03_3.png)
 * <p>
 * - Input Specification:
 * <p>
 * Each input file contains one test case. For each case, the first line contains a positive integer N (≤30) which is the total number of nodes in a tree (and hence the nodes are numbered from 1 to N). Then 2N lines follow, each describes a stack operation in the format: "Push X" where X is the index of the node being pushed onto the stack; or "Pop" meaning to pop one node from the stack.
 * <p>
 * - Output Specification:
 * <p>
 * For each test case, print the postorder traversal sequence of the corresponding tree in one line. A solution is guaranteed to exist. All the numbers must be separated by exactly one space, and there must be no extra space at the end of the line.
 * <p>
 * - Sample Input:
 * <p>
 * ```bash
 * 6
 * Push 1
 * Push 2
 * Push 3
 * Pop
 * Pop
 * Push 4
 * Pop
 * Pop
 * Push 5
 * Push 6
 * Pop
 * Pop
 * ```
 * - Sample Output:
 * <p>
 * ```bash
 * 3 4 2 6 5 1
 * ```
 */
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sum;
        sum = Integer.parseInt(reader.readLine());

        String[] inputs = new String[sum*2];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = reader.readLine();
        }

        Stack<Node> nodes = new Stack<>();
        String lastOperation = null;

        final String PUSH = "Push";
        final String POP = "Pop";

        StringBuilder sb = new StringBuilder();

        Node parent= null;

        for (int i = 0; i < inputs.length; i++) {
            String[] lineArg = inputs[i].split(" ");
            if (Objects.equals(lineArg[0], PUSH)) {
                Node node = new Node();
                node.data = Integer.parseInt(lineArg[1]);
                nodes.push(node);
                lastOperation = PUSH;
            } else {
                Node node = nodes.pop();
                if(Objects.equals(lastOperation, PUSH)){
                    sb.append(node.data);
                    sb.append(' ');
                    if(parent!=null){
                        sb.append(parent.data);
                        sb.append(' ');
                    }
                    parent = null;
                }else{
                    parent = node;
                }
                lastOperation = POP;
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    static class Node {
        int data = -1;
        int left = -1;
        int right = -1;
    }
}
