package cn.wycode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ## 作业1：树的同构
 * 给定两棵树T1和T2。如果T1可以通过若干次左右孩子互换就变成T2，则我们称两棵树是“同构”的。例如图1给出的两棵树就是同构的，因为我们把其中一棵树的结点A、B、G的左右孩子互换后，就得到另外一棵树。而图2就不是同构的。
 * 图1：
 * ![图1](/blog/images/20180326_data_structures03_1.png)
 * 图2：
 * ![图2](/blog/images/20180326_data_structures03_2.png)
 * 现给定两棵树，请你判断它们是否是同构的。
 * <p>
 * - 输入格式:
 * 输入给出2棵二叉树树的信息。对于每棵树，首先在一行中给出一个非负整数N (≤10)，即该树的结点数（此时假设结点从0到N−1编号）；随后N行，第i行对应编号第i个结点，给出该结点中存储的1个英文大写字母、其左孩子结点的编号、右孩子结点的编号。如果孩子结点为空，则在相应位置上给出`-`。给出的数据间用一个空格分隔。注意：题目保证每个结点中存储的字母是不同的。
 * <p>
 * - 输出格式:
 * 如果两棵树是同构的，输出`Yes`，否则输出`No`。
 * <p>
 * - 输入样例1（对应图1）：
 * ```bash
 * 8
 * A 1 2
 * B 3 4
 * C 5 -
 * D - -
 * E 6 -
 * G 7 -
 * F - -
 * H - -
 * 8
 * G - 4
 * B 7 6
 * F - -
 * A 5 1
 * H - -
 * C 0 -
 * D - -
 * E 2 -
 * ```
 * <p>
 * - 输出样例1:
 * ```bash
 * Yes
 * ```
 * - 输入样例2（对应图2）：
 * ```bash
 * 8
 * B 5 7
 * F - -
 * A 0 3
 * C 6 -
 * H - -
 * D - -
 * G 4 -
 * E 1 -
 * 8
 * D 6 -
 * B 5 -
 * E - -
 * H - -
 * C 0 2
 * G - 3
 * F - -
 * A 1 4
 * ```
 * - 输出样例2:
 * ```bash
 * No
 * ```
 */
public class Test08 {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //读第一个树节点
        int n_a = 0;
        n_a = Integer.parseInt(reader.readLine());

        String[] inputs_a = new String[n_a];
        List<Node> nodes_a = new ArrayList<>(n_a);

        for (int i = 0; i < n_a; i++) {
            inputs_a[i] = reader.readLine();
        }


        int n_b = 0;
        n_b = Integer.parseInt(reader.readLine());

        String[] inputs_b = new String[n_b];
        List<Node> nodes_b = new ArrayList<>(n_b);

        for (int i = 0; i < n_b; i++) {
            inputs_b[i] = reader.readLine();
        }

        if (n_a != n_b) {
            System.out.println("No");
            return;
        }

        addNodes(inputs_a, nodes_a);
        addNodes(inputs_b, nodes_b);

        if(inputs_a.length==1&&!Objects.equals(nodes_a.get(0).data,nodes_b.get(0).data)){
            System.out.println("No");
            return;
        }

        for (Node node_a : nodes_a) {
            for (Node node_b : nodes_b) {
                if (Objects.equals(node_a.data, node_b.data)) {
                    if (!((Objects.equals(node_a.left, node_b.left) && Objects.equals(node_a.right, node_b.right))
                            || (Objects.equals(node_a.left, node_b.right) && Objects.equals(node_a.right, node_b.left)))) {
                        System.out.println("No");
                        return;
                    }
                }
            }
        }

        System.out.println("Yes");
    }


    private static void addNodes(String[] inputs, List<Node> nodes) {
        for (String input : inputs) {
            Node n = new Node();
            String[] lineArg = input.split(" ");
            n.data = lineArg[0];
            if (!Objects.equals("-", lineArg[1])) {
                int left_num = Integer.parseInt(lineArg[1]);
                n.left = inputs[left_num].split(" ")[0];
            }
            if (!Objects.equals("-", lineArg[2])) {
                int right_num = Integer.parseInt(lineArg[2]);
                n.right = inputs[right_num].split(" ")[0];
            }
            nodes.add(n);
        }
    }

    static class Node {
        String data;
        String left;
        String right;
    }
}
