package data_structure.myLab.map.lab523;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/24
 * \* Time: 19:38
 * \* Description:
 * \
 */
public class User {

    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void hash(Object s) {
        System.out.println("==================================");
        int h = s.hashCode();
        System.out.println(Integer.toBinaryString(h).length());
        System.out.println(Integer.toBinaryString(h));
        int temp = h >>> 16;
        System.out.println(Integer.toBinaryString(temp));
        int newHash = h ^ temp;
        System.out.println(Integer.toBinaryString(newHash));
        System.out.println("==================================");
    }

    public static void main(String[] args) {

        User u1 = new User(1,"john");

        hash(u1);



    }
}
