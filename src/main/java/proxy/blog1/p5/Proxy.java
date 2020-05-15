package proxy.blog1.p5;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:39
 * \* Description:
 * \
 */
public class Proxy extends Subject{
    private RealSubject real;
    @Override
    public void Request() {
        if (null == real) {
            real = new RealSubject();
        }
        real.Request();
    }
}
/*
静态代理模式固然在访问无法访问的资源，
增强现有的接口业务功能方面有很大的优点，但是大量使用这种静态代理
，会使我们系统内的类的规模增大，并且不易维护；
并且由于 Proxy 和 RealSubject 的功能本质上是相同的，Proxy
只是起到了中介的作用，这种代理在系统中的存在，导致系统结构比较臃肿和松散。

 */