package com.mzh.dataStructures.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(array);

        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空！！");
        }
    }

    public static Node createHuffmanTree(int[] array) {

        List<Node> list = new ArrayList<Node>();
        for (int value : array) {
            list.add(new Node(value));
        }

        while (list.size() > 1) {
            Collections.sort(list);

            Node leftNode = list.get(0);
            Node rigthNode = list.get(1);

            Node parent = new Node(leftNode.value + rigthNode.value);
            parent.left = leftNode;
            parent.rigth = rigthNode;

            list.remove(leftNode);
            list.remove(rigthNode);

            list.add(parent);
        }

        return list.get(0);
    }

}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node rigth;

    public void preOrder(){

        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if (this.rigth!=null){
            this.rigth.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
