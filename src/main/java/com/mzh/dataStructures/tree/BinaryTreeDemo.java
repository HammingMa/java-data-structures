package com.mzh.dataStructures.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "吴用");
        HeroNode hero3 = new HeroNode(3, "卢俊义");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");

        BinaryTree tree = new BinaryTree(root);

        root.setLeft(hero2);
        root.setRight(hero3);
        hero3.setRight(hero4);
        hero3.setLeft(hero5);

        System.out.println("前序遍历");
        tree.preOrder();//1,2,3,4
        //1,2,3,5,4

        System.out.println("中序遍历");
        tree.infixOrder();//2,1,3,4
        //2,1,5,3,4

        System.out.println("后序遍历");
        tree.postOrder();//2,4,3,1
        //2,5,4,3,1

//        int id = 5;
//        HeroNode heroNode = tree.preOrderSearch(id);
//        System.out.println("前序查找");
//        if (heroNode != null) {
//            System.out.println("查找到节点的信息为 id = " + heroNode.getId() + " name = " + heroNode.getName());
//        } else {
//            System.out.println("没有找到编号为" + id + "的英雄");
//        }

//        int id = 5;
//        HeroNode heroNode = tree.infixOrderSearch(id);
//        System.out.println("中序查找");
//        if (heroNode != null) {
//            System.out.println("查找到节点的信息为 id = " + heroNode.getId() + " name = " + heroNode.getName());
//        } else {
//            System.out.println("没有找到编号为" + id + "的英雄");
//        }


//        int id = 15;
//        HeroNode heroNode = tree.postOrderSearch(id);
//        System.out.println("后序查找");
//        if (heroNode != null) {
//            System.out.println("查找到节点的信息为 id = " + heroNode.getId() + " name = " + heroNode.getName());
//        } else {
//            System.out.println("没有找到编号为" + id + "的英雄");
//        }

        System.out.println("删除前前序遍历");
        tree.preOrder();
        tree.deleteNode2(4);
        System.out.println("删除后前序遍历");
        tree.preOrder();


    }
}

class BinaryTree {
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
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

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
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
