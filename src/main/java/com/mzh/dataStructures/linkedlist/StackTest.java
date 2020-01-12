package com.mzh.dataStructures.linkedlist;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        stack.add("a");
        stack.add("b");
        stack.add("c");
        stack.add("e");

        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
