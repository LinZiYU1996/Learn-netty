package proxy.blog1.api;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:33
 * \* Description:
 * \
 */
public class ReflectClassDemo03 {

    enum E {
        A, B
    }
    public static void main(String[] args) {
        Class c = "foo".getClass();
        System.out.println(c.getCanonicalName());
        Class c2 = ReflectClassDemo03.E.A.getClass();
        System.out.println(c2.getCanonicalName());
        byte[] bytes = new byte[1024];
        Class c3 = bytes.getClass();
        System.out.println(c3.getCanonicalName());
        Set<String> set = new HashSet<>();
        Class c4 = set.getClass();
        System.out.println(c4.getCanonicalName());
    }

 /*
 java.lang.String
proxy.blog1.api.ReflectClassDemo03.E
byte[]
java.util.HashSet
  */
}
