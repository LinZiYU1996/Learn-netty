package rpc.pack1.myinter;

import rpc.pack1.provider.HelloService;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:04
 * \* Description:
 * \
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String msg) {
        return msg != null ? msg + " -----> I am fine." : "I am fine.";
    }
}
