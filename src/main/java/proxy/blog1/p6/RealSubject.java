package proxy.blog1.p6;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:42
 * \* Description:
 * \
 */
public class RealSubject implements Subject{

    @Override
    public void hello(String str) {
        System.out.println("Hello  " + str);
    }
    @Override
    public String bye() {
        System.out.println("Goodbye");
        return "Over";
    }


}
