package cn.wycode;

import java.util.Stack;

public class Test05 {

    public static void main(String[] args) {
        String s = "2*(6/3+4)-5";
        System.out.println("中缀表达式："+s);
        s= covert(s);
        System.out.println("转换为后缀表达式："+s);
        int i = calculate(s);
        System.out.println("计算后缀表达式："+i);

    }

    private static int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        for (char c : s.toCharArray()) { //从左到右遍历，如果是数字压入堆栈，如果是操作符，从堆栈中pop出两个数字计算，并将结果压入堆栈
            if(c=='+') {
                stack.push(stack.pop() + stack.pop());
            }else if(c=='*') {
                stack.push(stack.pop() * stack.pop());
            }else if(c=='-') {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            }else if(c=='/'){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            }else{
                stack.push(Character.getNumericValue(c));
            }
        }

        return stack.pop(); //循环结束时，栈顶的数字就是后缀表达式结果

    }

    private static String covert(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') { //如果是数字直接输出
                sb.append(c);
            } else if (c == '(') { //如果是左括号，压入操作堆栈
                stack.push(c);
            } else if (c == ')') { //如果是右括号
                while (true) { //寻找左括号
                    c = stack.pop();
                    if (c == '(') { //找到直接结束循环
                        break;
                    } else { //不是左括号直接输出操作符
                        sb.append(c);
                    }
                }
            } else { //如果是+ - * /

                while (true) {  //寻找栈顶比当前操作优先级低的操作符
                    if (stack.isEmpty()) { //如果是空栈，直接压入操作符
                        stack.push(c);
                        break;
                    }
                    char cInStack = stack.peek();
                    if (isStackPriority(cInStack, c)) { //如果栈顶操作符优先级高或相同，直接出栈并输出
                        sb.append(stack.pop());
                    } else {
                        stack.push(c); //找到优先级低的地方，把当前操作符push进去
                        break;
                    }
                }
            }
            //System.out.println(stack.size());
        }
        while (!stack.isEmpty()){ //最后将栈里的操作符全部输出
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private static boolean isStackPriority(char cInStack, char c2) {
        return cInStack != '(' && (cInStack == '*' || cInStack == '/' || c2 != '*' && c2 != '/');
    }
}
