package proxy.pack2;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:55
 * \* Description:
 * \
 */
public class HelloImp implements Hello{

    @Override
    public String sayHello(String str) {
        return "HelloImp: " + str;
    }
}
