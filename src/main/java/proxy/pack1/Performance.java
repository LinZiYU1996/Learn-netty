package proxy.pack1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:44
 * \* Description:
 * \
 */
public class Performance implements InvocationHandler {

    private TestSQL sql;

    public Performance(TestSQL sql) {
        this.sql = sql;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long begin = System.currentTimeMillis();

        // method.invoke 实际上就是调用 sql.query()
        Object object = method.invoke(sql, args);

        long end = System.currentTimeMillis();
        long step = end - begin;
        System.out.println("执行花费 ：" + step);
        return object;

    }

}
