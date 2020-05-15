package proxy.blog1.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 15:44
 * \* Description:
 * \
 */
public class StaticProxiedHello implements Hello{


    Logger logger = LoggerFactory.getLogger(StaticProxiedHello.class);

    private Hello hello = new HelloImp();
    @Override
    public String sayhello(String s) {
        logger.info("you say: " + s);
        return hello.sayhello(s);
    }
}
