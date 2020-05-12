package data_structure.stack;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/11
 * \* Time: 21:25
 * \* Description:
 * \
 */
public class MyStack {



    private int top;

    private int[] elemnts;

    private int size;


    public MyStack(int size) {

        top = -1;

        elemnts = new int[size];

        size = 0;
    }

    public boolean isEmpty() {
        return top == -1;

    }


    public boolean isFull() {

        return size == elemnts.length;

    }


    public void enStack(int num) {

        if (isFull()) {
            System.out.println("栈已满 进栈失败");
            return;
        } else {

            top++;
            elemnts[top] = num;
            size++;

        }

    }


    public int outStack() {

        if (isEmpty()) {
            System.out.println("栈空 无数据");
            return -999;
        }

        int d = elemnts[top];
        top--;
        size--;
        return  d;

    }


    @Override
    public String toString() {
        return "MyStack{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {

        MyStack myStack = new MyStack(10);

        System.out.println(myStack.toString());

        System.out.println("============================");


        myStack.enStack(1);

        myStack.enStack(2);

        myStack.enStack(3);

        System.out.println(myStack.toString());
        System.out.println("============================");

        while (!myStack.isEmpty()) {
            System.out.print(myStack.outStack()+"   ");
        }

        System.out.println("");

        System.out.println(myStack.toString());
        System.out.println("============================");

        for (int i = 0; i < 15; i++) {

            myStack.enStack(i);

        }

        System.out.println(myStack.toString());

    }













}
