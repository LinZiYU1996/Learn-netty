package proxy.pack1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:43
 * \* Description:
 * \
 */
public class PerformanceMonitor implements IDatabase{
    TestSQL sql;

    public PerformanceMonitor(TestSQL sql) {
        this.sql = sql;
    }


    @Override
    public void query() {
        long begin = System.currentTimeMillis();

        // 业务逻辑。
        sql.query();

        long end = System.currentTimeMillis();
        long step = end - begin;
        System.out.println("执行花费 : " + step);
    }


}
