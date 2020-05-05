package proxy.pack1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/4
 * \* Time: 16:43
 * \* Description:
 * \
 */
public class TestSQL implements IDatabase{

    @Override
    public void query() {
        System.out.println("执行 query。。。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
