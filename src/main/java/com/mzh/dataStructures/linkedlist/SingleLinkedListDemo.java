package com.mzh.dataStructures.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");


        SingleLinkedList heros = new SingleLinkedList();

        //heros.add(hero1);
        //heros.add(hero4);
        //heros.add(hero2);
        //heros.add(hero3);

        heros.addByOrder(hero1);
        heros.addByOrder(hero4);
        heros.addByOrder(hero3);
        heros.addByOrder(hero2);
        heros.addByOrder(hero2);

        heros.list();

        HeroNode newHero = new HeroNode(4, "鲁智深", "花和尚");
        HeroNode newHero2 = new HeroNode(10, "张飞", "黑旋风");

        heros.update(newHero);
        heros.update(newHero2);

        System.out.println("更新后的英雄!");
        heros.list();


        heros.delete(1);
        System.out.println("删除1号英雄后:");
        heros.list();
        //heros.delete(4);
        //System.out.println("删除4号英雄后:");
        //heros.list();
        //heros.delete(10);
        //heros.delete(2);
        //System.out.println("删除2号英雄后:");
        //heros.list();
        //heros.delete(3);
        //heros.delete(1);
        //System.out.println("删除全部英雄后:");
        //heros.list();

        System.out.println("单链表中有效节点的个数为：" + getLength(heros.getHead()));

        System.out.println(getLastIndexNode(heros.getHead(), 1));
        System.out.println(getLastIndexNode(heros.getHead(), 3));

        heros.addByOrder(newHero2);
        System.out.println("翻转前的链表");
        heros.list();

        reversetSingleList(heros.getHead());
        System.out.println("翻转后的链表");
        heros.list();

        System.out.println("逆序打印链表链表");
        reversePrintSingleList(heros.getHead());

        System.out.println("使用栈stack逆序打印链表链表");
        reversePrintSingleListByStack(heros.getHead());

        HeroNode hero5 = new HeroNode(5, "李逵", "黑旋风");
        HeroNode hero6 = new HeroNode(6, "李广", "小旋风");
        HeroNode hero7 = new HeroNode(7, "武松", "打虎英雄");
        HeroNode hero8 = new HeroNode(8, "孙二娘", "母夜叉");
        HeroNode hero9 = new HeroNode(9, "张顺", "浪里白条");

        hero1.next = null;
        hero2.next = null;
        hero3.next = null;
        hero4.next = null;

        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();

        list1.addByOrder(hero1);
        list1.addByOrder(hero4);
        list1.addByOrder(hero5);

        list2.addByOrder(hero2);
        list2.addByOrder(hero3);
        list2.addByOrder(hero6);
        list2.addByOrder(hero7);
        list2.addByOrder(hero8);
        list2.addByOrder(hero9);


        System.out.println("list1:");
        list1.list();
        System.out.println("list2:");
        list2.list();
        System.out.println("mergeList:");
        SingleLinkedList mergeList = mergeOrderSingleLinkedList(list1.getHead(), list2.getHead());
        mergeList.list();
    }


    //求单链表中有效节点的个数
    public static int getLength(HeroNode head) {
        int length = 0;
        if (head.next == null) {
            System.out.println("链表为空");
            return length;
        }

        HeroNode temp = head.next;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    //查找单链表中倒数第k个元素
    public static HeroNode getLastIndexNode(HeroNode head, int k) {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }

        int length = getLength(head);

        if (k <= 0 || k > length) {
            System.out.println("查找的位置非法");
            return null;
        }

        HeroNode currNode = head.next;

        for (int i = 0; i < length - k; i++) {
            currNode = currNode.next;
        }

        return currNode;
    }

    //单链表的翻转
    public static void reversetSingleList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode currNode = head.next;
        //把head 摘出来
        head.next = null;
        HeroNode nextNode = null;

        while (currNode != null) {
            nextNode = currNode.next;
            //先把当前节点摘出来
            currNode.next = null;
            //当前节点的下一个节点为haed的下一个节点
            currNode.next = head.next;
            //当前节点插入到head后面
            head.next = currNode;

            currNode = nextNode;
        }
    }

    //逆序打印单链表
    public static void reversePrintSingleList(HeroNode head) {
        if (head.next == null) {
            System.out.println(head);
        } else {
            HeroNode temp = head.next;
            reversePrintSingleList(temp);
            System.out.println(head);
        }
    }


    //逆序打印单链表
    public static void reversePrintSingleListByStack(HeroNode head) {
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode currentNode = head.next;

        while (currentNode != null) {
            stack.add(currentNode);
            currentNode = currentNode.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表
    public static SingleLinkedList mergeOrderSingleLinkedList(HeroNode head1, HeroNode head2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        HeroNode currentNode1 = head1.next;
        HeroNode currentNode2 = head2.next;
        HeroNode temp;
        while (currentNode1 != null && currentNode2 != null) {

            if (currentNode1.id < currentNode2.id) {
                temp = currentNode1;
                currentNode1 = currentNode1.next;
                temp.next =null;
                singleLinkedList.add(temp);

            } else {
                temp = currentNode2;
                currentNode2 = currentNode2.next;
                temp.next =null;
                singleLinkedList.add(temp);

            }
        }

        if (currentNode1 == null) {
            singleLinkedList.add(currentNode2);
        } else {
            singleLinkedList.add(currentNode1);
        }


        return singleLinkedList;
    }

}

class SingleLinkedList {
    private HeroNode head = new HeroNode(-1, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {
        HeroNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean isExist = false;

        while (temp.next != null) {
            if (temp.next.id > heroNode.id) {
                break;
            } else if (temp.next.id == heroNode.id) {
                isExist = true;
                break;
            }

            temp = temp.next;
        }

        if (isExist) {
            System.out.println("添加的编号为" + heroNode.id + "的英雄已经存在，不能添加");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode heroNode) {

        if (head.next == null) {
            System.out.println("链表为空，不能修改");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;

        while (temp != null) {
            if (temp.id == heroNode.id) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到要更改的英雄");
        }

    }

    public void delete(int id) {
        if (head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }

        HeroNode temp = this.head;
        boolean isFind = false;

        while (temp.next != null) {
            if (temp.next.id == id) {
                isFind = true;
                break;
            }

            temp = temp.next;
        }

        if (isFind) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到要删除的" + id + "号英雄");
        }

    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空，不能遍历");
            return;
        }

        HeroNode temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }
}


class HeroNode {
    int id;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
