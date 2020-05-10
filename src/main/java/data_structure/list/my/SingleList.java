package data_structure.list.my;


import org.jboss.marshalling.OutputStreamByteOutput;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/7
 * \* Time: 22:20
 * \* Description:
 * \
 */
public class SingleList<E> {

    // 头结点
    private Node<E> head;

    // 尾结点
    private Node<E> tail;

    // 元素个数
    private int size;

    // 初始化
    public SingleList() {

        head = null;

        tail = null;

        size = 0;
    }


    // 往头部插入数据e
    public void insertToHead(E e){

        Node insertNode = new Node(e);

        if (head == null){
            head = insertNode;
            tail = insertNode;
            size++;
        } else {
            insertNode.next = head;
            head = insertNode;
            size++;
        }
    }

    // 尾部插入数据 e
    public void insertToTail(E e){

        Node insertNode = new Node(e);
        if (head == null){
            head = insertNode;
            tail = insertNode;
            head.next=null;
            tail.next=null;
            size++;
        } else if (size == 1){
            head.next = insertNode;
            tail = insertNode;
            size++;
        } else {
            tail.next = insertNode;
            tail = insertNode;
            size++;
        }
    }

    // 顺序输出元素
    public void display(){
        Node p = head;
        while (p!=null){
            System.out.print(p.e + "    ");
            p = p.next;
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return "SingleList{" +
                "head=" + head.e +
                ", tail=" + tail.e +
                ", size=" + size +
                '}';
    }


    // 往头部插入集合中的元素
    public void insertToHeadByArrays(List<E> list){

        for (E e:list){
            insertToHead(e);
        }
    }

    // 往尾部插入集合中的元素
    public void insertToTailByArrays(List<E> list){

        for (E e:list){
            insertToTail(e);
        }
    }

    // 在目标结点后面插入新的结点
    public void insertAfterNode(E target,E new_e){

        Node target_Node = findByValue(target);
        Node insertNode = new Node(new_e);
        if (target_Node == head) {
            Node p_next = head.next;
            head.next = insertNode;
            insertNode.next = p_next;
            size++;
        } else if (target_Node == tail){
            tail.next = insertNode;
            tail = insertNode;
            size++;
        } else {
            Node p_next = target_Node.next;
            target_Node.next = insertNode;
            insertNode.next = p_next;
            size++;
        }

    }


    // 根据值找到对应的第一个结点
    public Node findByValue(E e){

        Node p = head;

        while (p != null) {
            if (p.e == e){
                return p;
            }
            p = p.next;
        }

        return null;
    }


    public void deleteNodeByVal(E e) {

        Node target = findByValue(e);

        if (target == null) {
            System.out.println("结点不存在，删除操作失败");
        }

        if (target == head) {
            Node temp = head;

            head = head.next;

            temp = null;

            size--;
        } else {

            Node p = head;
            Node pre = null;
            while (p != target) {

                pre = p;
                p = p.next;
            }

            if (p == tail) {

                pre.next = null;
                p = null;
                size--;
            } else {

                pre.next = p.next;

                p = null;

                size--;

            }


        }

    }

    public void updateByVal(E e,E new_e) {

        Node target = findByValue(e);

        if (target == head) {
            head.e = new_e;
        } else if (target == tail) {
            tail.e = new_e;
        } else {
            Node p = head;

            while (p != target) {
                p = p.next;
            }

            p.e = new_e;
        }
    }


    public static void main(String[] args) {


        SingleList<Integer> singleList = new SingleList<>();

        singleList.insertToTail(10);
        singleList.insertToTail(2);
        singleList.insertToTail(3);
        singleList.insertToTail(10);
        singleList.insertToTail(10);
        singleList.findByValue(10);
        singleList.display();
        singleList.deleteNodeByVal(10);
        singleList.display();
        singleList.updateByVal(10,775);
        singleList.display();

//        singleList.insertAfterHead(1);
//        singleList.insertAfterHead(2);

//        System.out.println(singleList.size);
//        System.out.println("=======================================");
//        singleList.display();
//        System.out.println("=======================================");
//        List<Integer> datas = new LinkedList<>();
//
//        int[] data = {1,2,3,4,5,6,7,8,9,10};
//
//        for (int i = 0; i < data.length; i++) {
//            datas.add(data[i]);
//        }
//
////        singleList.insertToHeadByArrays(datas);
//
//        singleList.insertToTailByArrays(datas);
//        singleList.display();
//        System.out.println("=======================================");
//
//        System.out.println(singleList.toString());
//
//        singleList.insertAfterNode(5,666);
//
//        singleList.display();
//
//        System.out.println(singleList.toString());
//        System.out.println("=======================================");
//
//        singleList.insertAfterNode(1,666);
//
//        singleList.display();
//
//        System.out.println(singleList.toString());
//        System.out.println("=======================================");
//
//        singleList.insertAfterNode(10,666);
//
//        singleList.display();
//
//        System.out.println(singleList.toString());
//
//
//        System.out.println("=======================================");
//
//        singleList.deleteNodeByVal(1);
//
//        singleList.display();
//
//        System.out.println(singleList.toString());
//
//
//        System.out.println("=======================================");
//
//        singleList.updateByVal(666,7758);
//        singleList.display();
//        System.out.println("=======================================");


    }
}
