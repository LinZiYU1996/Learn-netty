package data_structure.list.my;

import flash.pack3.Packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/9
 * \* Time: 21:46
 * \* Description:
 * \
 */
public class DoubleList<E> {


    private class Node {

        E e;

        Node prior;

        Node next;

        public void setE(E e) {
            this.e = e;
        }

        public void setPrior(Node prior) {
            this.prior = prior;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;

    private Node tail;

    private int size;

    public DoubleList() {

        head = null;

        tail = null;

        size = 0;
    }

    public void addAfterToHead(E e) {

        Node node = new Node();
        node.setE(e);
        node.setNext(null);
        node.setPrior(null);

        if (size == 0) {
            head = tail = node;
            size++;
        } else {
           node.setNext(head);
           head.setPrior(node);
           head = node;
            size++;
        }
    }

    public void addAfterToTail(E e) {
        Node node = new Node();
        node.setE(e);
        node.setNext(null);
        node.setPrior(null);

        if (size == 0 ) {
            head = tail = node;
            size++;
        } else if (size == 1) {
            head.setNext(node);
            node.setPrior(head);
            tail = node;
            size++;
        } else {
            tail.setNext(node);
            node.setPrior(tail);
            tail = node;
            size++;
        }

    }

    public void display_reverse() {

        Node p = tail;

        while (p != null) {

            System.out.print(p.e + "  ");

            p = p.prior;
        }

        System.out.println("");
    }

    public void display() {

        Node p = head;

        while (p!=null) {
            System.out.print(p.e + "   ");
            p = p.next;
        }
        System.out.println("");
    }

    public void addArraysAfterToHead(List<E> list) {

        for (E e : list) {
            addAfterToHead(e);
        }
    }


    public void addArraysAfterToTail(List<E> list) {

        for (E e : list) {
            addAfterToTail(e);
        }
    }

    public Node findByVal(E val) {

        Node p = head;

        while (p!=null) {
            if (p.e == val) {
                return p;
            }
            p = p.next;
        }

        return null;
    }
    public void insertAfterTargetNodeByVal(E val, E insert) {

        Node targetNode = findByVal(val);
        Node insertNode = new Node();
        insertNode.setPrior(null);
        insertNode.setNext(null);
        insertNode.setE(insert);
        if (targetNode == head) {
            insertNode.setNext(head.next);
            head.next.setPrior(insertNode);
            head.setNext(insertNode);
            insertNode.setPrior(head);
            size++;
        } else if (targetNode == tail) {

            tail.setNext(insertNode);
            insertNode.setPrior(tail);
            tail = insertNode;
            size++;
        } else {
            insertNode.setNext(targetNode.next);
            targetNode.next.setPrior(insertNode);
            insertNode.setPrior(targetNode);
            targetNode.setNext(insertNode);
            size++;

        }
    }


    @Override
    public String toString() {
        return "DoubleList{" +
                "head=" + head.e +
                ", tail=" + tail.e +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {

        DoubleList<Integer> doubleList = new DoubleList<>();

        int[] d = {1,2,3,4,5,6,7,8,9};
        List<Integer> dlist =  Arrays.stream(d)
                .boxed()
                .collect(Collectors.toList());


//        System.out.println("============================");
//        doubleList.addArraysAfterToHead(dlist);
//        doubleList.display();
//        System.out.println(doubleList.toString());
//        System.out.println("============================");

        System.out.println("============================");
        doubleList.addArraysAfterToTail(dlist);
        doubleList.display();
        System.out.println(doubleList.toString());
        System.out.println("============================");

        System.out.println("============================");


        doubleList.display_reverse();

        System.out.println("============================");

        System.out.println("============================");

        doubleList.insertAfterTargetNodeByVal(5,101);

        doubleList.display();
        System.out.println(doubleList.toString());

        System.out.println("============================");

        System.out.println("============================");
        doubleList.insertAfterTargetNodeByVal(1,102);
        doubleList.display();

        System.out.println(doubleList.toString());

        System.out.println("============================");

        System.out.println("============================");
        doubleList.insertAfterTargetNodeByVal(9,103);
        doubleList.display();
        System.out.println(doubleList.toString());

        System.out.println("============================");

    }



}
