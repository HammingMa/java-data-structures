package com.mzh.dataStructures.hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        EmpLinkedListArray emps = new EmpLinkedListArray(2);

        Emp emp1 = new Emp(1, "张三");
        Emp emp2 = new Emp(2, "张四");
        Emp emp3 = new Emp(3, "张五");
        Emp emp4 = new Emp(6, "张六");
        Emp emp5 = new Emp(8, "张七");
        Emp emp6 = new Emp(10, "张八");

        emps.add(emp1);
        emps.add(emp6);
        emps.add(emp3);
        emps.add(emp5);
        emps.add(emp2);
        emps.add(emp4);


        emps.list();

        emps.find(3);
        emps.find(5);

        System.out.println("删除======");
        emps.delete(1);
        emps.delete(8);
        emps.delete(5);

        emps.delete(3);

        emps.list();

    }
}

class EmpLinkedListArray {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public EmpLinkedListArray(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    private int hash(int no) {
        return no % size;
    }

    public void add(Emp emp) {
        int index = hash(emp.no);
        //empLinkedListArray[index].add(emp);
        empLinkedListArray[index].addByOrder(emp);
    }

    public void list() {

        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i + 1);
        }

        System.out.println();
    }

    public void find(int no) {
        int index = hash(no);

        Emp emp = empLinkedListArray[index].find(no);
        if (emp == null) {
            System.out.println("在第" + (index + 1) + "条链表中没有查找到！");
        } else {
            System.out.println("查找的结果为：" + emp.toString());
        }
    }


    public void delete(int no) {
        int index = hash(no);

        empLinkedListArray[index].delete(no, index);

    }


}


class EmpLinkedList {
    private Emp head = null;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }

        curEmp.next = emp;
    }

    public void addByOrder(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        if (head.no > emp.no) {
            emp.next = head;
            head = emp;
            return;
        }

        Emp curEmp = head;

        while (curEmp.next != null && curEmp.next.no < emp.no) {
            curEmp = curEmp.next;
        }

        emp.next = curEmp.next;
        curEmp.next = emp;

    }

    public void list(int index) {
        if (head == null) {
            System.out.println("第" + index + "条链表为空！");
            return;
        }

        Emp curEmp = head;
        System.out.print("第" + index + "条链表:");
        while (curEmp != null) {
            System.out.printf(" => " + curEmp.toString());
            curEmp = curEmp.next;
        }
        System.out.println();

    }

    public Emp find(int no) {
        if (head == null) {
            return null;
        }

        Emp curEmp = head;
        while (curEmp != null && curEmp.no != no) {
            curEmp = curEmp.next;
        }

        return curEmp;
    }

    public void delete(int no, int index) {
        if (head == null) {
            System.out.println("第" + index + "条链表为空，不存在要删除的雇员！");
        }

        if (head.no == no) {
            Emp deleteEmp = head;
            head = head.next;
            System.out.println("在第" + index + "条链表中" + deleteEmp + "删除成功！");
            return;
        }

        boolean isExist = false;

        Emp curEmp = head;
        while (curEmp.next != null) {
            if (curEmp.next.no == no) {
                isExist = true;
                break;
            }
            curEmp = curEmp.next;
        }

        if (isExist) {
            Emp deleteEmp = curEmp.next;
            curEmp.next = curEmp.next.next;
            System.out.println("在第" + index + "条链表中" + deleteEmp + "删除成功！");
        } else {
            System.out.println("在第" + index + "条链表中，不存在要删除的雇员！");
        }

    }
}


class Emp {
    public int no;
    public String name;
    public Emp next;

    public Emp(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

