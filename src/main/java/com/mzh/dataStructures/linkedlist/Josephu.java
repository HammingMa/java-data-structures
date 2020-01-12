package com.mzh.dataStructures.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList boys = new CircleSingleLinkedList();

        boys.addBoy(5);
        boys.listBoy();

        boys.countBoy(1,2,5);
    }
}


class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("数量不合法，不能添加！！");
            return;
        }

        first = new Boy(1);
        first.setNext(first);

        Boy curBoy = first;

        for (int i = 2; i <= nums; i++) {
            Boy boy = new Boy(i);
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = curBoy.getNext();
        }

    }

    public void listBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;

        do {
            System.out.println("小孩的编号为：" + curBoy.getNo());
            curBoy = curBoy.getNext();
        } while (curBoy != first);

    }

    public void countBoy(int startNo,int countNum ,int nums){
        if(first ==null){
            System.out.println("链表为空，没有小孩不能数数！");
        }

        if(startNo<1 || startNo>nums){
            System.out.println("输入的参数有误！");
            return;
        }

        Boy helper = this.first;

        while (helper.getNext() != first){
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while(first.getNext() != first){
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println("出圈的小孩的编号为："+first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最终留在圈内的小孩的编号为："+ first.getNo());
    }
}


class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}