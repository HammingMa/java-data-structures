package com.mzh.dataStructures.tree;

public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7,};

        ArrayBinaryTree tree = new ArrayBinaryTree(array);
        System.out.println("前序遍历");
        tree.preOrder();

        System.out.println("中序遍历");
        tree.infixOrder();

        System.out.println("后序遍历");
        tree.postOrder();
    }
}

class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder(){
        if (array == null || array.length == 0) {
            System.out.println("二叉树为空不能遍历");
            return;
        }
        preOrder(0);
    }

    public void preOrder(int index) {

        System.out.println(array[index]);

        if ((2 * index + 1) < array.length) {
            preOrder(2 * index + 1);
        }

        if ((2 * index + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }


    public void infixOrder(){
        if (array == null || array.length == 0) {
            System.out.println("二叉树为空不能遍历");
            return;
        }
        infixOrder(0);
    }


    public void infixOrder(int index) {


        if ((2 * index + 1) < array.length) {
            infixOrder(2 * index + 1);
        }

        System.out.println(array[index]);

        if ((2 * index + 2) < array.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder(){
        if (array == null || array.length == 0) {
            System.out.println("二叉树为空不能遍历");
            return;
        }
        postOrder(0);
    }

    public void postOrder(int index) {


        if ((2 * index + 1) < array.length) {
            postOrder(2 * index + 1);
        }

        if ((2 * index + 2) < array.length) {
            postOrder(2 * index + 2);
        }

        System.out.println(array[index]);
    }

}