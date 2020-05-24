package data_structure.myLab.map.lab523;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/23
 * \* Time: 22:13
 * \* Description:
 * \
 */
public class TesttableSizeFor {

//找到大于或等于 cap 的最小2的幂

    static int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;System.out.println(Integer.toBinaryString(n));

        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    public static void main(String[] args) {

        System.out.println("=============================");
        System.out.println(tableSizeFor(10));// 16
        System.out.println("=============================");
        System.out.println(tableSizeFor(123));// 128
        System.out.println("=============================");
        System.out.println(tableSizeFor(1024));// 1024
        System.out.println("=============================");
        System.out.println(tableSizeFor(2041));// 1024

        System.out.println("=============================");
        System.out.println(tableSizeFor(556));// 1024

        System.out.println("=============================");
        System.out.println(tableSizeFor(87));// 1024

        System.out.println("=============================");
        System.out.println("=============================");
        System.out.println("=============================");
        System.out.println("=============================");

    }
}
//=============================
//1101
//1111
//1111
//1111
//1111
//16
//=============================
//1111111
//1111111
//1111111
//1111111
//1111111
//128
//=============================
//1111111111
//1111111111
//1111111111
//1111111111
//1111111111
//1024
//=============================
//11111111100
//11111111111
//11111111111
//11111111111
//11111111111
//2048
//=============================
//1100111111
//1111111111
//1111111111
//1111111111
//1111111111
//1024
//=============================
//1111111
//1111111
//1111111
//1111111
//1111111
//128

