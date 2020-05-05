package proxy.pack2;

import java.util.logging.Logger;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:55
 * \* Description:
 * \
 */
public class StaticProxiedHello implements Hello{



    private Hello hello = new HelloImp();
    @Override
    public String sayHello(String str) {
//        logger.info("You said: " + str);
        return hello.sayHello(str);
    }
}
