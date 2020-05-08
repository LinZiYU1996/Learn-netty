package data_structure.list;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/7
 * \* Time: 22:01
 * \* Description:
 * \
 */
public class LinkedList<E> {

    private class Node{
        private  E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedList() {
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /*
    向链表指定位置添加元素
     */

    public void add(int index, E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add faild. Illegal index.");
        }

        Node pre = dummyHead;

        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(e, pre.next);

        size++;
    }

    /**
     * 向链表头添加元素
     *
     */

    public void addFirst(E e){

        add(0, e);
    }

    /*
     * 获取指定位置元素

     */

    public E get(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Get faild. Illegal index.");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public void printf(){
        Node p = dummyHead.next;
        while (p.next != null){
            System.out.println(p.e);
            p = p.next;
        }
    }


    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        System.out.println(linkedList.size);
        linkedList.addFirst(100);

        linkedList.addFirst(122);
        System.out.println(linkedList.size);

        linkedList.printf();


    }
}
