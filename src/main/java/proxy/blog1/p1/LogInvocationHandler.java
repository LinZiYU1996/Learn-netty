package proxy.blog1.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 15:45
 * \* Description:
 * \
 */
public class LogInvocationHandler implements InvocationHandler {

    Logger logger = LoggerFactory.getLogger(LogInvocationHandler.class);

    private Hello hello;

    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayhello".equals(method.getName())) {

            logger.info("You said: " + Arrays.toString(args));
        }
        return method.invoke(hello, args);
    }

    public static void main(String[] args) {

        Hello hello = (Hello) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class<?>[] {Hello.class},
                new LogInvocationHandler(new HelloImp())
        );


        System.out.println(hello.sayhello("i am man"));

    }
}
