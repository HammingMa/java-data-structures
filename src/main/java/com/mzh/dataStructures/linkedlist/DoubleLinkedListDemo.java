package com.mzh.dataStructures.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();

        //linkedList.add(hero1);
        //linkedList.add(hero2);
        //linkedList.add(hero3);
        //linkedList.add(hero4);

        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);

        System.out.println("添加后的链表");
        linkedList.list();

        HeroNode2 newHero4 = new HeroNode2(4, "公孙胜", "入云龙");
        linkedList.update(newHero4);
        System.out.println("修改后的链表");
        linkedList.list();

        linkedList.delete(3);
        linkedList.delete(4);
        System.out.println("删除后的链表");
        linkedList.list();

    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(-1, "", "");

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空，不能遍历");
            return;
        }

        HeroNode2 temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 heroNode) {

        if (head.next == null) {
            System.out.println("链表为空，不能修改");
            return;
        }

        HeroNode2 temp = head.next;
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

        HeroNode2 temp = this.head.next;
        boolean isFind = false;

        while (temp != null) {
            if (temp.id == id) {
                isFind = true;
                break;
            }

            temp = temp.next;
        }

        if (isFind) {
            temp.pre.next = temp.next.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有找到要删除的" + id + "号英雄");
        }

    }

    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            if(temp.next != null){
                temp.next.pre = heroNode;
            }
            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next =heroNode;

        }
    }
}

class HeroNode2 {
    int id;
    String name;
    String nickName;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int id, String name, String nickName) {
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