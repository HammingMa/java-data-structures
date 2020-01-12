package com.mzh.dataStructures.tree.avltree;

public class AvlTreeDemo {
    public static void main(String[] args) {
        //int[] array = {4, 3, 6, 5, 7, 8};

        //int[] array = {10, 12, 8, 9, 7, 6};
        int[] array = {10, 11, 7, 6, 8, 9};

        AvlTree tree = new AvlTree();

        for (int i : array) {
            tree.add(new Node(i));
        }

        tree.infixOrder();

        System.out.println("左旋后数的高度");
        System.out.println("树的高度为：" + tree.getRoot().height());
        System.out.println("左子树的高度为：" + tree.getRoot().leftHeight());
        System.out.println("右子树的高度为：" + tree.getRoot().rigthHeight());
        System.out.println("当前的根节点：" + tree.getRoot());
        System.out.println(tree.getRoot().left.rigth);

    }
}

class AvlTree {


    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }

        return this.left.height();
    }

    //右子树的高度
    public int rigthHeight() {
        if (this.rigth == null) {
            return 0;
        }

        return this.rigth.height();
    }

    //以当前节点为根点的数的高度的高度
    public int height() {
        int leftHeight = 0;
        int rigthHeight = 0;
        if (this.left != null) {
            leftHeight = this.left.height();
        }
        if (this.rigth != null) {
            rigthHeight = this.rigth.height();
        }

        int height = Math.max(leftHeight, rigthHeight);

        return height + 1;
    }


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

    //左旋
    public void leftRotate() {
        Node newNode = new Node(this.value);

        newNode.left = this.left;
        newNode.rigth = this.rigth.left;

        this.value = this.rigth.value;

        this.rigth = this.rigth.rigth;

        this.left = newNode;


    }

    //右旋
    public void rigthRotate() {
        Node newNode = new Node(this.value);

        newNode.rigth = this.rigth;
        newNode.left = this.left.rigth;

        this.value = this.left.value;
        this.left = this.left.left;
        this.rigth = newNode;
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

        if (rigthHeight() - leftHeight() > 1) {

            //当前节点的右子节点的左子树的高度大于当前节点的右子节点的右子树的高度，需要先对当前节点的的右子节点进行左旋，然后对当前节点进行右旋
            if(this.rigth !=null && this.rigth.leftHeight()>this.rigthHeight()){
                rigthRotate();
            }

            leftRotate();

            return;
        }

        if (leftHeight() - rigthHeight() > 1) {

            //当前节点的左节点的右子树的高度大于当前节点的左节点的左子树的高度，需要先对 当前节点的左节点进行左旋，然后右旋
            if (this.left != null && this.left.rigthHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
            }

            rigthRotate();
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
