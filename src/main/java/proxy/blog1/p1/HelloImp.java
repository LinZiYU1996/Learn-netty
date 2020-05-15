package proxy.blog1.p1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 15:44
 * \* Description:
 * \
 */
public class HelloImp implements Hello{

    @Override
    public String sayhello(String s) {
        return "HelloImp  " + s;
    }
}
