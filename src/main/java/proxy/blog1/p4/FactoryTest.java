package proxy.blog1.p4;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/14
 * \* Time: 16:23
 * \* Description:
 * \
 */

/**
 * @author hongten
 * @created Apr 18, 2018
 */
public class FactoryTest {

    public static void main(String[] args) {
        //Cats cats = FactoryTest.getInstance("com.b510.hongten.test.reflex.Lion");
        Cats cats = FactoryTest.getInstance("com.b510.hongten.test.reflex.Tiger");
        cats.eatMeat();

        /*
         * The Result : The tiger eat meat.
         */
    }

    public static Cats getInstance(String name) {
        Cats cats = null;
        try {
            try {
                //use Class.forName() with java reflection
                cats = (Cats) Class.forName(name).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cats;
    }
}

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