package com.mzh.dataStructures.tree.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] array = {7, 3, 10, 12, 5, 1, 9, 13};

        BinarySortTree tree = new BinarySortTree();

        for (int i : array) {
            tree.add(new Node(i));
        }

        tree.infixOrder();

        tree.deleteNode(7);
        tree.deleteNode(3);
        tree.deleteNode(12);
        tree.deleteNode(5);
        tree.deleteNode(9);
        tree.deleteNode(1);
        tree.deleteNode(10);
        tree.deleteNode(13);



        System.out.println("删除后");
        tree.infixOrder();


    }
}

class BinarySortTree {
    Node root;

    private Node serach(int value) {
        if (root == null) {
            return null;
        }

        return root.search(value);
    }

    private Node searchParent(int value) {
        if (root == null) {
            return null;
        }

        return root.searchParent(value);
    }


    /**
     * 查找以node为根节点的子树的 最小值 并且删除
     *
     * @param node
     * @return
     */
    private int serachRightMin(Node node) {
        Node target = node;

        while (target.left != null) {
            target = target.left;
        }

        int min = target.value;

        deleteNode(min);

        return min;
    }

    public void deleteNode(int value) {
        if (root == null) {
            return;
        }

        Node targetNode = serach(value);
        //没有找到要删除的节点
        if (targetNode == null) {
            return;
        }

        Node parentNode = searchParent(value);
        //二叉树只有一个要删除的节点 则要删除的节点为root节点
        if (parentNode == null) {
            //只有一个root节点的二叉树
            if (root.left == null && root.rigth == null) {
                root = null;
                return;
            } else if (root.left != null || root.rigth != null) { //root 有一个左节点或一个由节点
                root = root.left != null ? root.left : root.rigth;
            }

            //如果root 有两个节点在情况 已经在下面处理 没有问题
        }


        //System.out.println(targetNode);
        //System.out.println(parentNode);
        //删除的为叶子节点
        if (targetNode.left == null && targetNode.rigth == null) {
            //删除左节点
            if (parentNode.left != null && parentNode.left.value == value) {
                parentNode.left = null;
            } else if (parentNode.rigth != null && parentNode.rigth.value == value) {
                // 删除右节点
                parentNode.rigth = null;
            }
        } else if (targetNode.left != null && targetNode.rigth != null) { //删除有两个子节点的
            int min = serachRightMin(targetNode.rigth);
            targetNode.value = min;
        } else { //删除只有一个子节点的
            if (targetNode.left != null) { //要删除的节点 只有左节点
                if (parentNode.left != null && parentNode.left.value == value) { //删除的节点为左节点
                    parentNode.left = targetNode.left;
                } else if (parentNode.rigth != null && parentNode.rigth.value == value) { //删除的节点为右节点
                    // 删除右节点
                    parentNode.rigth = targetNode.left;
                }
            } else if (targetNode.rigth != null) { //要删除的节点 只有右节点
                if (parentNode.left != null && parentNode.left.value == value) { //删除的节点为左节点
                    parentNode.left = targetNode.rigth;
                } else if (parentNode.rigth != null && parentNode.rigth.value == value) { //删除的节点为右节点
                    // 删除右节点
                    parentNode.rigth = targetNode.rigth;
                }
            }
        }

    }


    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            return;
        } else {
            root.infixOrder();
        }
    }

}


class Node {
    int value;
    Node left;
    Node rigth;


    //查找要删除的节点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.rigth != null) {
                return this.rigth.search(value);
            } else {
                return null;
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.rigth != null && this.rigth.value == value)) {
            return this;
        } else if (this.value > value && this.left != null) {
            return this.left.searchParent(value);
        } else if (this.value <= value && this.rigth != null) {
            return this.rigth.searchParent(value);
        } else {
            return null;
        }

    }

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (this.value > node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.rigth == null) {
                this.rigth = node;
            } else {
                this.rigth.add(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.rigth != null) {
            this.rigth.infixOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}