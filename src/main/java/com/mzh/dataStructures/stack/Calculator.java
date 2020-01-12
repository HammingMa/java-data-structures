package com.mzh.dataStructures.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "100+9+6*2-5*2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int num1 = 0;
        int num2 = 0;
        char oper = ' ';
        int result = 0;
        String keepNum = "";



        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    if (operStack.priority(ch) > operStack.priority((char) operStack.peek())) {
                        operStack.push(ch);
                    } else {
                        System.out.println(ch);
                        oper = (char) operStack.pop();
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        result = operStack.cal(num1, num2, oper);
                        numStack.push(result);

                        operStack.push(ch);
                    }
                }
            } else {
                keepNum += ch;
                if (i == chars.length - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum="";
                } else {
                    if (operStack.isOper(chars[i + 1])) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
        }

        while (!operStack.isEmpty()) {
            oper = (char) operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = operStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.println(expression + " = " + numStack.pop());

    }
}

class ArrayStack2 {
    private int maxSize;
    private int stack[];
    private int top;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public Boolean isFull() {
        return top == (maxSize - 1);
    }

    public Boolean isEmpty() {
        return top == -1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满不能入栈！！！");
            return;
        }

        stack[++top] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，不能出栈");
        }

        return stack[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空不能遍历！");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d   ", i, stack[i]);
        }

        System.out.println();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，不能出栈");
        }

        return stack[top];
    }

    //返回定义的运算符的优先级
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '*' || oper == '/') {
            return 0;
        } else {
            return -1;
        }

    }

    //返回是否是符号
    public Boolean isOper(char ch) {
        if (ch == '*' || ch == '/' || ch == '+' || ch == '-') {
            return true;
        } else {
            return false;
        }
    }

    // 计算结果
    public int cal(int num1, int num2, char oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }

        System.out.println(""+num2+oper+num1+"="+result);
        return result;
    }

}