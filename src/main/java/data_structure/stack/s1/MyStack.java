package data_structure.stack.s1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/11
 * \* Time: 21:55
 * \* Description:
 * \
 */
public class MyStack<E> {


    private Node top;

    private int size;

    private class Node {

        E data;

        Node next;



        public Node(E data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node() {
        }
    }






    public MyStack() {

        top = new Node();
        top.next = null;

        size = 0;
    }




    public boolean isEmpty() {

        return top.next == null;
    }


    public void en(E e) {

        Node d = new Node(e);

        d.setNext(top.next);
        top.setNext(d);
        size++;
    }

    public E out() {

        if (isEmpty()) {
            System.out.println("栈空 无数据了");
            return null;
        } else {

            E e = top.next.data;
            top.next = top.next.next;
            size--;
            return e;
        }

    }


    public Node getTop() {

        if (top.next == null) {
            System.out.println("空栈");
            return null;
        }

        return top.next;
    }


    public void getM() {

        if (top.next == null) {
            System.out.println("空栈");
            return;
        } else {

            System.out.println("top : " + top.next.data
            + "   size : " + size);
        }



    }

    public static void main(String[] args) {

        MyStack<Integer> myStack = new MyStack<>();


        myStack.getM();

        System.out.println("===================================");


        for (int i = 0; i < 15; i++) {
            myStack.en(i);
        }

        myStack.getM();

        System.out.println("===================================");

        myStack.out();



        myStack.getM();

        System.out.println("===================================");


        while (!myStack.isEmpty()) {

            System.out.print(myStack.out() + "  ");


        }


        System.out.println("===================================");

        myStack.getM();

        System.out.println("===================================");


    }


}
