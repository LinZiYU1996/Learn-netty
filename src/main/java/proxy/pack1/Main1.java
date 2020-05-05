package proxy.pack1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:44
 * \* Description:
 * \
 */
public class Main1 {

    public static void main(String[] strings) {
        TestSQL sql = new TestSQL();

        PerformanceMonitor performanceMonitor = new PerformanceMonitor(sql);
        // 由 Performance 代替 testSQL 执行
        performanceMonitor.query();
    }


}
