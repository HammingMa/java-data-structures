package com.mzh.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolanNotation {
    public static void main(String[] args) {
        String expression = "100+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);

        List<String> list = parseSuffixExpressionList(infixExpressionList);

        System.out.println(list);


        Stack<Integer> stack = new Stack<>();

        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(Integer.parseInt(s));
            } else {

                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                Integer result = 0;
                switch (s) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                }

                stack.push(result);
            }
        }

        System.out.println("计算结果为：" + stack.pop());

    }

    public static List<String> toInfixExpressionList(String expression) {

        List<String> infixExpressionList = new ArrayList<>();

        String num = "";

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch < 48 || ch > 57) {
                infixExpressionList.add(String.valueOf(ch));
            } else if (ch >= 48 && ch <= 57) {
                num += ch;

                if (i + 1 == chars.length || chars[i + 1] < 48 || chars[i + 1] > 57) {
                    infixExpressionList.add(num);
                    num = "";
                }
            }
        }

        return infixExpressionList;
    }

    public static List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        Stack<String> operaStack = new Stack<>();
        List<String> resultList = new ArrayList<>();

        for (String s : infixExpressionList) {
            if (s.matches("\\d+")) {
                resultList.add(s);
            } else if ("(".equals(s)) {
                operaStack.push(s);
            } else if (")".equals(s)) {
                while (!"(".equals(operaStack.peek())) {
                    resultList.add(operaStack.pop());
                }
                operaStack.pop();
            } else {
                while (!operaStack.isEmpty() && Operation.getValue(operaStack.peek()) >= Operation.getValue(s)) {
                    resultList.add(operaStack.pop());
                }
                operaStack.push(s);
            }
        }

        while (!operaStack.isEmpty()) {
            resultList.add(operaStack.pop());
        }


        return resultList;
    }
}

class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operaton) {
        int result = 0;

        switch (operaton) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("输入的符号有误!");
                break;
        }
        return result;
    }
}

