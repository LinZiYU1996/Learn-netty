package data_structure.myLab.List.Lab522;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/22
 * \* Time: 22:49
 * \* Description:
 * \
 */
public class MyArrayList<T> {


    private Object[] container;

    private int size;

    public MyArrayList() {
        container = new Object[10];
        size = 0;
    }

    public void add(T t) {
        ensureCapaciry(container.length + 1);
        container[size++] = t;

    }

    private void ensureCapaciry(int add_o_affter) {
        if (add_o_affter > container.length) {
            resize(add_o_affter);
        }
    }

    private void resize(int add_o_affter) {
        int newcapacity = add_o_affter + 10;


        Object[] temp = container;

        container = new Object[newcapacity];

        for (int i = 0; i < temp.length; i++) {
            container[i] = temp[i];
        }

    }

    private T get(int index) {

        return (T)container[index];

    }

    public static void main(String[] args) {

        MyArrayList<Integer> myArrayList = new MyArrayList<>();

        myArrayList.add(1);

        System.out.println(myArrayList.get(0));

    }

}
