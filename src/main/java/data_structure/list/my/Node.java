package data_structure.list.my;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/7
 * \* Time: 22:18
 * \* Description:
 * \
 */
public class Node<E> {

    public E e;

    public Node next;

    public Node(E e, Node next) {
        this.e = e;
        this.next = next;
    }

    public Node(E e) {
        this.e = e;
    }

    public Node() {
    }

    public void setE(E e) {
        this.e = e;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
