package proxy.blog1.api;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:33
 * \* Description:
 * \
 */
public class ReflectClassDemo02 {

    public static void main(String[] args) {
        Boolean b;
        // Class c = b.getClass(); // 编译错误
        Class c1 = Boolean.class;
        System.out.println(c1.getCanonicalName());
        Class c2 = java.io.PrintStream.class;
        System.out.println(c2.getCanonicalName());
        Class c3 = int[][][].class;
        System.out.println(c3.getCanonicalName());
    }

//    java.lang.Boolean
//    java.io.PrintStream
//    int[][][]
}
