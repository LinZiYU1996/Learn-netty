package proxy.pack1;

import java.lang.reflect.Proxy;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:45
 * \* Description:
 * \
 */
public class Main2 {

    public static void main(String[] strings) {
        TestSQL sql = new TestSQL();
        Performance performance = new Performance(sql);

        /*
        第一个参数为类加载器
        第二个参数为要代理的接口，在这个例子里面就是 IDatabase 接口。
        第三个参数为实现 InvocationHandler 接口的对象。
         */
        IDatabase proxy = (IDatabase) Proxy.newProxyInstance(
                sql.getClass().getClassLoader(),
                sql.getClass().getInterfaces(),
                performance
        );
        proxy.query();
    }

}
