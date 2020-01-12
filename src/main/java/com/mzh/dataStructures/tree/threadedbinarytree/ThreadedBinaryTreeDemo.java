package com.mzh.dataStructures.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "吴用");
        HeroNode hero3 = new HeroNode(3, "卢俊义");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");
        HeroNode hero6 = new HeroNode(6, "李逵");

        root.setLeft(hero2);
        root.setRight(hero3);
        hero2.setLeft(hero4);
        hero2.setRight(hero5);
        hero3.setLeft(hero6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);

        System.out.println("中序线索化前遍历 中序遍历");
        tree.infixOrder();

        tree.threadedNode();
        System.out.println("中序线索化后遍历");
        tree.threadedList();


        HeroNode left = hero5.getLeft();
        System.out.println("5号节点的左节点：" + left);
        HeroNode right = hero5.getRight();
        System.out.println("5号节点的右节点：" + right);


    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void threadedNode() {
        threadedNode(root);
    }

    //遍历中序线索化后的二叉树
    public void threadedList() {
        HeroNode node = this.root;

        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }

    }


//    public void preOrderThreadedNode() {
//        preOrderThreadedNode(root);
//    }
//
//    //前序线索化
//    public void preOrderThreadedNode(HeroNode node) {
//        if (node == null) {
//            return;
//        }
//
//        //线索化 当前节点的左指针为空 指向前驱
//        if (node.getLeft() == null) {
//            node.setLeft(pre);
//            node.setLeftType(1);
//        }
//        //当前节点的前驱节点的右指针为空 指向当前节点
//        if (pre != null && pre.getRight() == null) {
//            pre.setRight(node);
//            pre.setRightType(1);
//        }
//        //为下一个节点的前驱节点
//        pre = node;
//
//        //左子树线索化
//        preOrderThreadedNode(node.getLeft());
//
//
//        //右子树线索化
//        preOrderThreadedNode(node.getRight());
//
//    }

    //中序线索化
    public void threadedNode(HeroNode node) {
        if (node == null) {
            return;
        }

        //左子树线索化
        threadedNode(node.getLeft());

        //线索化 当前节点的左指针为空 指向前驱
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //当前节点的前驱节点的右指针为空 指向当前节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //为下一个节点的前驱节点
        pre = node;

        //右子树线索化
        threadedNode(node.getRight());
    }


    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public HeroNode preOrderSearch(int id) {
        if (root != null) {
            return root.preOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public HeroNode infixOrderSearch(int id) {
        if (root != null) {
            return root.infixOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public HeroNode postOrderSearch(int id) {
        if (root != null) {
            return root.postOrderSearch(id);
        } else {
            System.out.println("二叉树为空");
            return null;
        }
    }

    public void deleteNode(int id) {
        if (root != null) {
            if (root.getId() == id) {
                root = null;
            } else {
                root.deleteNode(id);
            }
        } else {
            System.out.println("二叉树为空，不能删除");
        }
    }

    public void deleteNode2(int id) {
        if (root != null) {
            if (root.getId() == id) {
                root = root.getLeft();
            } else {
                root.deleteNode2(id);
            }
        } else {
            System.out.println("二叉树为空，不能删除");
        }
    }


}


class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //左指针的类型 0 代表左子树 1 代表前驱节点
    private int leftType;
    //右指针的类型 0 代表右子树 1 代表后继节点
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode preOrderSearch(int id) {
        HeroNode resultNode = null;

        System.out.println("进入前序查找");
        if (this.id == id) {
            resultNode = this;
        }

        if (resultNode == null && this.left != null) {
            resultNode = this.left.preOrderSearch(id);
        }

        if (resultNode == null && this.right != null) {
            resultNode = this.right.preOrderSearch(id);
        }

        return resultNode;
    }

    public HeroNode infixOrderSearch(int id) {
        HeroNode resultNode = null;

        if (this.left != null) {
            resultNode = this.left.infixOrderSearch(id);
        }

        System.out.println("进入中序查找");
        if (resultNode == null && this.id == id) {
            resultNode = this;
        }

        if (resultNode == null && this.right != null) {
            resultNode = this.right.infixOrderSearch(id);
        }

        return resultNode;
    }

    public HeroNode postOrderSearch(int id) {
        HeroNode resultNode = null;

        if (this.left != null) {
            resultNode = this.left.postOrderSearch(id);
        }

        if (resultNode == null && this.right != null) {
            resultNode = this.right.postOrderSearch(id);
        }

        System.out.println("进入后序查找");
        if (resultNode == null && this.id == id) {
            resultNode = this;
        }

        return resultNode;
    }

    public boolean deleteNode(int id) {
        boolean isDelete = false;

        if (this.left != null && this.left.getId() == id) {
            this.left = null;
            isDelete = true;
        }

        if (!isDelete && this.right != null && this.right.getId() == id) {
            this.right = null;
            isDelete = true;
        }


        if (!isDelete && this.left != null) {
            isDelete = this.left.deleteNode(id);
        }

        if (!isDelete && this.right != null) {
            isDelete = this.right.deleteNode(id);
        }

        return isDelete;
    }


    public boolean deleteNode2(int id) {
        boolean isDelete = false;

        if (this.left != null && this.left.getId() == id) {
            this.left = this.left.left;
            isDelete = true;
        }

        if (!isDelete && this.right != null && this.right.getId() == id) {
            this.right = this.right.left;
            isDelete = true;
        }


        if (!isDelete && this.left != null) {
            isDelete = this.left.deleteNode(id);
        }

        if (!isDelete && this.right != null) {
            isDelete = this.right.deleteNode(id);
        }

        return isDelete;
    }

}