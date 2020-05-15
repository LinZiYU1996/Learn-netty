package proxy.blog1.p5;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:39
 * \* Description:
 * \
 */
public class RealSubject extends Subject{

    @Override
    public void Request() {
        System.out.println("真实的请求");
    }
}
