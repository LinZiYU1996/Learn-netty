package rpc.blog1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/7
 * \* Time: 9:32
 * \* Description:
 * \
 */
public class HelloServiceImpl implements HelloService{

    public String hello(String name) {
        return "Hello " + name;
    }
}
