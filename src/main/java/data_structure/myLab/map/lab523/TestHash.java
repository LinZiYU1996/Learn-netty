package data_structure.myLab.map.lab523;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/23
 * \* Time: 22:55
 * \* Description:
 * \
 */
public class TestHash {

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public static void main(String[] args) {

        String s = "123asas";

        System.out.println(Integer.toBinaryString(s.hashCode()));


        int h = s.hashCode();
        int temp = h >>> 16;
        int newHash = h ^ temp;



        System.out.println(Integer.toBinaryString(newHash));

    }
}

//1111000011000000010100100010110
//111100001100000
//1111000011000000101000101110110

//1111000011000000010100100010110
//111100001100000010100010111011


