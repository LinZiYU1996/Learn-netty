package data_structure.myLab.map.lab523;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/23
 * \* Time: 22:24
 * \* Description:
 * \
 */
public class T2 {

    public static void main(String[] args) {

        int n = 6;

        System.out.println(Integer.toBinaryString(n));
        System.out.println(n = n >>> 1);//3
        System.out.println(Integer.toBinaryString(n));
        System.out.println();

        int m = 45;

        System.out.println(Integer.toBinaryString(m));
        System.out.println(m = m >>> 1);//3
        System.out.println(Integer.toBinaryString(m));
        System.out.println();
//110
//3
//11

        //101101
        //22
        //10110

    }
}
