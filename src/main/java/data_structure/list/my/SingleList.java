package data_structure.list.my;


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

    private Node<E> head;

    private Node<E> tail;

    private int size;
    public SingleList() {

        head = null;

        tail = null;

        size = 0;
    }

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

    public void insertToHeadByArrays(List<E> list){

        for (E e:list){
            insertToHead(e);
        }
    }

    public void insertToTailByArrays(List<E> list){

        for (E e:list){
            insertToTail(e);
        }
    }

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


    public static void main(String[] args) {


        SingleList<Integer> singleList = new SingleList<>();

//        singleList.insertAfterHead(1);
//        singleList.insertAfterHead(2);

        System.out.println(singleList.size);
        System.out.println("=======================================");
        singleList.display();
        System.out.println("=======================================");
        List<Integer> datas = new LinkedList<>();

        int[] data = {1,2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < data.length; i++) {
            datas.add(data[i]);
        }

//        singleList.insertToHeadByArrays(datas);

        singleList.insertToTailByArrays(datas);
        singleList.display();
        System.out.println("=======================================");

        System.out.println(singleList.toString());

        singleList.insertAfterNode(5,666);

        singleList.display();

        System.out.println(singleList.toString());
        System.out.println("=======================================");

        singleList.insertAfterNode(1,666);

        singleList.display();

        System.out.println(singleList.toString());
        System.out.println("=======================================");

        singleList.insertAfterNode(10,666);

        singleList.display();

        System.out.println(singleList.toString());
        System.out.println("=======================================");
    }
}
