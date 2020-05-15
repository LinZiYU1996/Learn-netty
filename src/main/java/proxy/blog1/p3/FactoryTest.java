package proxy.blog1.p3;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:21
 * \* Description:
 * \
 */

/**
 * @author hongten
 * @created Apr 18, 2018
 */
public class FactoryTest {

    public static void main(String[] args) {
        Cats cats = FactoryTest.getInstance("Tiger");
        cats.eatMeat();

        /*
         * The Result : The tiger eat meat.
         */
    }

    public static Cats getInstance(String name) {
        Cats cats = null;
        if ("Tiger".equals(name)) {
            cats = new Tiger();
        }
        if ("Lion".equals(name)) {
            cats = new Lion();
        }
        return cats;
    }
}


/*
我们会发现：

当我们在添加一个子类（Puma-美洲狮）的时候，就需要修改工厂类了。
如果我们添加太多的子类的时候，改的就会很多。

我们对程序进行修改，加入反射机制。
 */
interface Cats {
    public abstract void eatMeat();
}

class Tiger implements Cats {

    public void eatMeat() {
        System.out.println("The tiger eat meat.");
    }

}

class Lion implements Cats {

    public void eatMeat() {
        System.out.println("The lion eat meat.");
    }

}